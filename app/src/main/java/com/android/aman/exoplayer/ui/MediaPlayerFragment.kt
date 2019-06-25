package com.android.aman.exoplayer.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.aman.exoplayer.BuildConfig
import com.android.aman.exoplayer.R
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_media_player.view.*

class MediaPlayerFragment : androidx.fragment.app.Fragment() {

    private lateinit var root : View
    private lateinit var playerView : PlayerView
    private var player : SimpleExoPlayer? = null
    private var playWhenReady : Boolean = true
    private var currentWindow : Int = 0
    private var playbackPosition : Long = 0

    private lateinit var mediaUriList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_media_player, container, false)
        playerView = root.player_view
        validateData()
        setAdInUI()
        return root
    }

    private fun setAdInUI() {
        val topAdBanner = root.topAdBanner
        val adTopRequest = AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build()
        topAdBanner.loadAd(adTopRequest)

        val bottomBar = root.bottomAdBanner
        val adBottomRequest = AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build()
        bottomBar.loadAd(adBottomRequest)
    }

    @Suppress("UNCHECKED_CAST")
    private fun validateData() {
        arguments?.let {
            arguments?.getSerializable(KEY_URI)?.let {
                if(it is ArrayList<*> && it.isNotEmpty() && it[0] is String) {
                    mediaUriList = it as ArrayList<String>
                } else {
                    mediaUriList = arrayListOf()
                    playerView.visibility = View.GONE
                }
            } ?: run {
                mediaUriList = arrayListOf()
                playerView.visibility = View.GONE
            }
        } ?: run {
            mediaUriList = arrayListOf()
            playerView.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(context),
            DefaultTrackSelector(), DefaultLoadControl()
        )

        playerView.player = player
        player?.let {
            it.playWhenReady = playWhenReady
            it.seekTo(currentWindow, playbackPosition)

            val mediaSource = buildMediaSource(*mediaUriList.toTypedArray())
            it.prepare(mediaSource, true, false)
        }
    }

    private fun buildMediaSource(vararg uris: String): MediaSource {
        val mediaSourceList : ArrayList<ExtractorMediaSource> = arrayListOf()
        var uri : Uri
        for(uriString in uris) {
            uri = Uri.parse(uriString)
            val mediaSource = ExtractorMediaSource.Factory(
                DefaultHttpDataSourceFactory(BuildConfig.APPLICATION_ID)
            ).createMediaSource(uri)
            mediaSourceList.add(mediaSource)
        }

        return ConcatenatingMediaSource(*mediaSourceList.toTypedArray())

    }

    private fun releasePlayer() {
        player?.let {
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            it.release()
            player = null
        }
    }

    companion object {
        const val KEY_URI = "content_uri_string"
    }
}