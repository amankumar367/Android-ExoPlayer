package com.android.aman.exoplayer.ui

import com.android.aman.exoplayer.api.data.ChannelList

data class VideoPlayerState(
    var isLoading: Boolean,
    var channelListSuccess: Boolean,
    var message: String,
    var channelList: ChannelList?) {

    fun showLoading(): VideoPlayerState {
        return VideoPlayerState(true, false, "Loading.....", null)
    }
    fun showSuccessChannelList(channelList: ChannelList?): VideoPlayerState {
        return VideoPlayerState(false, true, "Load Again", channelList)
    }

    fun showError(message: String): VideoPlayerState {
        return VideoPlayerState(false, false, message, null)
    }
}