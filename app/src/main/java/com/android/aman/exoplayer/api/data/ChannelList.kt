package com.android.aman.exoplayer.api.data


import com.google.gson.annotations.SerializedName

data class ChannelList(
    @SerializedName("channels")
    val channels: List<Channel?>?
)