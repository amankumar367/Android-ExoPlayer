package com.android.aman.exoplayer.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.aman.exoplayer.R
import com.android.aman.exoplayer.api.data.ChannelList
import com.android.aman.exoplayer.api.repo.ChannelRepository
import com.android.aman.exoplayer.databinding.ActivityVideoPlayerBinding
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioRendererEventListener
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.exoplayer2.video.VideoRendererEventListener
import kotlinx.android.synthetic.main.activity_video_player.*
import java.io.Serializable

class VideoPlayerActivity : AppCompatActivity() {

    private var liveTvUrl : String? = null
    private val channelRepository = ChannelRepository()
    private lateinit var viewModel: VideoPlayerViewModel
    private lateinit var databinding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setObserber()
    }

    private fun init() {
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_video_player)
        viewModel = ViewModelProviders.of(this).get(VideoPlayerViewModel::class.java)
        viewModel.setRepositoryI(channelRepository)
        viewModel.getChhannel()
    }

    private fun setObserber() {
        viewModel.state.observe(this, object: Observer<VideoPlayerState>{
            override fun onChanged(t: VideoPlayerState?) {
                setUiState(t)
                if(t != null)
                    Log.d("ChannelList",""+ t.message)
                else
                    Log.d("ChannelList","" + t!!.message)
            }

        })

        viewModel.channelList.observe(this, object: Observer<ChannelList>{
            override fun onChanged(t: ChannelList?) {
                if(t != null){
                    liveTvUrl = t.channels!![0]!!.livetvUrl3g
                    startFragment(liveTvUrl)
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

    private fun startFragment(liveTvUrl: String?) {
        val url = arrayListOf("http://streamer5.vidgyor.com/vod-origin/promos/asianet/asianet_promo_2.mp4",
            "http://streamer5.vidgyor.com/vod-origin/promos/asianet/asianet_promo_1.mp4")
        val fragment = MediaPlayerFragment()
        val bundle = Bundle()
        bundle.putSerializable(MediaPlayerFragment.KEY_URI, url as Serializable)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.video_player, fragment).commit()
    }

}
