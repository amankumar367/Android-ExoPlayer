package com.android.aman.exoplayer.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.aman.exoplayer.api.data.ChannelList
import com.android.aman.exoplayer.api.repo.ChannelRepositoryI
import com.android.aman.exoplayer.api.retrofit.ApiCallBack

class VideoPlayerViewModel : ViewModel() {

    var channelList = MutableLiveData<ChannelList>()
    private lateinit var repositoryI: ChannelRepositoryI
    internal var state = MutableLiveData<VideoPlayerState>()

    fun setRepositoryI(channelRepositoryI: ChannelRepositoryI){
        this.repositoryI = channelRepositoryI
    }

    fun getChhannel(){
//        state.value = state.value!!.showLoading()
        repositoryI.getChannel(object: ApiCallBack<ChannelList>{
            override fun onSuccess(t: ChannelList) {
                channelList.value = t
//                state.value = state.value!!.showSuccessChannelList(t)
            }

            override fun onFailure(message: String) {
//                state.value = state.value!!.showError(message)
            }
        })
    }


}