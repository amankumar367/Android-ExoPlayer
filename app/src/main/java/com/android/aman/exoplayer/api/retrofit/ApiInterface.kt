package com.android.aman.exoplayer.api.retrofit

import com.android.aman.exoplayer.api.data.ChannelList
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInterface {

    // Request method and URL Given in the notation
    @GET("/test/lib_app_multiple_assignment.json")
    fun getChannel() : Observable<ChannelList>
}