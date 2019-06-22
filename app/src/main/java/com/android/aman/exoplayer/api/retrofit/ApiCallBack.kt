package com.android.aman.exoplayer.api.retrofit

interface ApiCallBack<T> {
    abstract fun onSuccess(t: T)
    abstract fun onFailure(message: String)
}