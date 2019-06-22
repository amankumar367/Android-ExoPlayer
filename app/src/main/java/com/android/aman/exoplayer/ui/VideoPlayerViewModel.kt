package com.android.aman.exoplayer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.aman.exoplayer.api.data.ChannelList
import com.android.aman.exoplayer.api.repo.ChannelRepositoryI
import com.android.aman.exoplayer.api.retrofit.ApiCallBack

class VideoPlayerViewModel : ViewModel() {

    private lateinit var repositoryI: ChannelRepositoryI
    internal var state = MutableLiveData<VideoPlayerState>()
    private var channelList: ChannelList? = null

    init {
        state.value = state.value!!.copy(false,
            false,
            "",
            channelList)
    }

    fun setRepositoryI(channelRepositoryI: ChannelRepositoryI){
        this.repositoryI = channelRepositoryI
    }

    fun getChhannel(){
        state.value = state.value!!.showLoading()
        repositoryI.getChannel(object: ApiCallBack<ChannelList>{
            override fun onSuccess(t: ChannelList) {
                state.value = state.value!!.showSuccessChannelList(t)
            }

            override fun onFailure(message: String) {
                state.value = state.value!!.showError(message)
            }
        })
    }


}