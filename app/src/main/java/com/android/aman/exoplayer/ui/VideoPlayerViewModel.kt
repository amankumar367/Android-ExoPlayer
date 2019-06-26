package com.android.aman.exoplayer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.aman.exoplayer.api.data.ChannelList
import com.android.aman.exoplayer.api.repo.ChannelRepositoryI
import com.android.aman.exoplayer.api.retrofit.ApiCallBack

class VideoPlayerViewModel : ViewModel() {

    private lateinit var repositoryI: ChannelRepositoryI

    internal var state = MutableLiveData<VideoPlayerState>()

    fun setRepositoryI(channelRepositoryI: ChannelRepositoryI){
        this.repositoryI = channelRepositoryI
    }

    fun getChhannel(){
        state.postValue(VideoPlayerState.showLoading())
        repositoryI.getChannel(object: ApiCallBack<ChannelList>{
            override fun onSuccess(t: ChannelList) {
                state.postValue(VideoPlayerState.showSuccessChannelList(t))
            }

            override fun onFailure(message: String) {
                state.postValue(VideoPlayerState.showError(message))
            }
        })
    }
}