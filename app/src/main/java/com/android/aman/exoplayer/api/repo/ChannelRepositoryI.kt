package com.android.aman.exoplayer.api.repo

import com.android.aman.exoplayer.api.data.ChannelList
import com.android.aman.exoplayer.api.retrofit.ApiCallBack


interface ChannelRepositoryI {
    abstract fun getChannel(apiCallBack: ApiCallBack<ChannelList>)
}