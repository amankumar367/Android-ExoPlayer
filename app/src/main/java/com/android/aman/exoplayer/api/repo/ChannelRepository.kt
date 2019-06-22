package com.android.aman.exoplayer.api.repo

import com.android.aman.exoplayer.api.data.ChannelList
import com.android.aman.exoplayer.api.retrofit.ApiCallBack
import com.android.aman.exoplayer.api.retrofit.ApiInterface
import com.android.aman.exoplayer.app.MainApplication
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class ChannelRepository : ChannelRepositoryI {

    @Inject
    internal lateinit var mRetrofit: Retrofit
    private var channelListApi: ApiInterface? = null


    init {
        MainApplication.getAppComponent()!!.inject(this)
        channelListApi = mRetrofit.create(ApiInterface::class.java)
    }


    override fun getChannel(apiCallBack: ApiCallBack<ChannelList>) {
        channelListApi!!.getChannel().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<ChannelList>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(channelList: ChannelList) {
                    apiCallBack.onSuccess(channelList)
                }

                override fun onError(e: Throwable) {
                    apiCallBack.onFailure(e.localizedMessage)
                }

            })

        }
}