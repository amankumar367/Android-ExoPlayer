package com.android.aman.exoplayer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.aman.exoplayer.R
import com.android.aman.exoplayer.api.repo.ChannelRepository

class MainActivity : AppCompatActivity() {

    private val channelRepository: ChannelRepository()
    private lateinit var viewModel: VideoPlayerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setObserber()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(VideoPlayerViewModel::class.java)
        viewModel.setRepositoryI(channelRepository)
        viewModel.getChhannel()
    }

    private fun setObserber() {
        viewModel.state.observe(this, object: Observer<VideoPlayerState>{
            override fun onChanged(t: VideoPlayerState?) {
            }

        })
    }
}
