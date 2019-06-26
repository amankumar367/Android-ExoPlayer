package com.android.aman.exoplayer.ui

import com.android.aman.exoplayer.api.data.ChannelList

data class VideoPlayerState(
    var isSuccess:  Boolean = false,
    var isLoading: Boolean = false,
    var message: String = "",
    var channelList: ChannelList? = null ) {

    companion object {
        fun showLoading(): VideoPlayerState {
            return VideoPlayerState(isLoading = true, message =  "Loading.....")
        }
        fun showSuccessChannelList(channelList: ChannelList): VideoPlayerState {
            return VideoPlayerState(
                isSuccess = true,
                isLoading = false,
                message = "Loaded Successfully",
                channelList = channelList
            )
        }

        fun showError(message: String): VideoPlayerState {
            return VideoPlayerState(false, message = message)
        }
    }
}