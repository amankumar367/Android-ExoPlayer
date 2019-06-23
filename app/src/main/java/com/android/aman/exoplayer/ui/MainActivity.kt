package com.android.aman.exoplayer.ui

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.aman.exoplayer.R
import com.android.aman.exoplayer.api.data.ChannelList
import com.android.aman.exoplayer.api.repo.ChannelRepository
import com.android.aman.exoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var liveTvUrl : String? = null
    private val channelRepository = ChannelRepository()
    private lateinit var viewModel: VideoPlayerViewModel
    private lateinit var databinding: ActivityMainBinding

    private lateinit var player: SimpleExoPlayer
    private lateinit var mediaDataSourceFactory: DataSource.Factory

    private var trackSelector: DefaultTrackSelector? = null
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setObserber()
    }

    private fun init() {
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(VideoPlayerViewModel::class.java)
        viewModel.setRepositoryI(channelRepository)
        viewModel.getChhannel()
    }

    private fun setObserber() {
        viewModel.state.observe(this, object: Observer<VideoPlayerState>{
            override fun onChanged(t: VideoPlayerState?) {
                setUiState(t)
                if(t != null)
                    Log.d("ChannelList",""+ t!!.message)
                else
                    Log.d("ChannelList","" + t!!.message)
            }

        })

        viewModel.channelList.observe(this, object: Observer<ChannelList>{
            override fun onChanged(t: ChannelList?) {
                if(t != null){
                    liveTvUrl = t.channels!![0]!!.livetvUrl3g
                    Log.d("ChannelList",""+ t.channels)
                }
                else
                    Log.d("ChannelList","List is empty")
            }
        })
    }

    private fun setUiState(t: VideoPlayerState?) {
        databinding.state = t
    }

    private fun initializePlayer() {

        trackSelector = DefaultTrackSelector()
        mediaDataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "mediaPlayerSample"))

        val mediaSource = ExtractorMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(Uri.parse(liveTvUrl.toString()))

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)


        with(player) {
            prepare(mediaSource, false, false)
            playWhenReady = true
        }


        playerView.setShutterBackgroundColor(Color.TRANSPARENT)
        playerView.player = player
        playerView.requestFocus()

    }

    private fun releasePlayer() {
        updateStartPosition()
        player.release()
        trackSelector = null
    }

    private fun updateStartPosition() {

        with(player) {
            playbackPosition = currentPosition
            currentWindow = currentWindowIndex
            playWhenReady = playWhenReady
        }
    }

    public override fun onStart() {
        super.onStart()

        if (Util.SDK_INT > 23) initializePlayer()
    }

    public override fun onResume() {
        super.onResume()

        if (Util.SDK_INT <= 23) initializePlayer()
    }

    public override fun onPause() {
        super.onPause()

        if (Util.SDK_INT <= 23) releasePlayer()
    }

    public override fun onStop() {
        super.onStop()

        if (Util.SDK_INT > 23) releasePlayer()
    }
}
