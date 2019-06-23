package com.android.aman.exoplayer.ui

import com.android.aman.exoplayer.api.data.ChannelList

data class VideoPlayerState(
    var isLoading: Boolean,
    var message: String) {

    private lateinit var channelList: ChannelList
    constructor(isLoading: Boolean, message: String, channelList: ChannelList)
            : this(isLoading, message){
        this.channelList = channelList!!
    }


    fun showLoading(): VideoPlayerState {
        return VideoPlayerState(true, "Loading.....")
    }
    fun showSuccessChannelList(channelList: ChannelList): VideoPlayerState {
        return VideoPlayerState(false, "Loading Successfull", channelList)
    }

    fun showError(message: String): VideoPlayerState {
        return VideoPlayerState(false, message)
    }

}