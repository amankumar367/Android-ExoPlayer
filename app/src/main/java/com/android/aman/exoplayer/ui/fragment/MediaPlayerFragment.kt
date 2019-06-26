package com.android.aman.exoplayer.ui.fragment

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.fragment_media_player.view.*
import kotlin.math.max

class MediaPlayerFragment : Fragment(), OnBackPressedI {

    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var root : View
    private lateinit var playerView : PlayerView
    private var player : SimpleExoPlayer? = null
    private var playWhenReady : Boolean = true
    private var currentWindow : Int = 0
    private var playbackPosition : Long = 0

    private lateinit var mediaUriList : ArrayList<String>
    private lateinit var livetvPreAdUrl : ArrayList<String>
    private lateinit var liveTvInterstitalAdTime : ArrayList<Int>
    private lateinit var livetvMidAdUrl : ArrayList<String>
    private lateinit var topAdBanner : AdView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_media_player, container, false)
        playerView = root.player_view
        validateLiveTvURLData()
        validateLiveTvPreAdURLData()
        validateLiveTvMidURLData()
        validateInterstitalData()
        setAdUI()
        return root
    }

    private fun setAdUI() {
        topAdBanner = root.topAdBanner
        topAdBanner.loadAd(AdRequest.Builder().build())

        val bottomBar = root.bottomAdBanner
        bottomBar.loadAd(AdRequest.Builder().build())

        mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())
    }

    @Suppress("UNCHECKED_CAST")
    private fun validateLiveTvURLData() {
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

    @Suppress("UNCHECKED_CAST")
    private fun validateLiveTvPreAdURLData() {
        arguments?.let {
            arguments?.getSerializable(PRE_AD)?.let {
                if(it is ArrayList<*> && it.isNotEmpty() && it[0] is String) {
                    livetvPreAdUrl = it as ArrayList<String>
                } else {
                    livetvPreAdUrl = arrayListOf()
                }
            } ?: run {
                livetvPreAdUrl = arrayListOf()
            }
        } ?: run {
            livetvPreAdUrl = arrayListOf()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun validateLiveTvMidURLData() {
        arguments?.let {
            arguments?.getSerializable(MID_AD)?.let {
                if(it is ArrayList<*> && it.isNotEmpty() && it[0] is String) {
                    livetvMidAdUrl = it as ArrayList<String>
                } else {
                    livetvMidAdUrl = arrayListOf()
                }
            } ?: run {
                livetvMidAdUrl = arrayListOf()
            }
        } ?: run {
            livetvMidAdUrl = arrayListOf()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun validateInterstitalData() {
        arguments?.let {
            arguments?.getSerializable(INTERSTITAL_TIME)?.let {
                if(it is ArrayList<*> && it.isNotEmpty() && it[0] is Int) {
                    liveTvInterstitalAdTime = it as ArrayList<Int>
                } else {
                    liveTvInterstitalAdTime = arrayListOf()
                }
            } ?: run {
                liveTvInterstitalAdTime = arrayListOf()
            }
        } ?: run {
            liveTvInterstitalAdTime = arrayListOf()
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

    override fun onBackPressed(): Boolean {
        val playerTime = player!!.contentPosition
        if(playerTime >= liveTvInterstitalAdTime[0] * 1000){
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            }
        }
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let {
            player?.let {
                it.playWhenReady = savedInstanceState.getBoolean(PLAYER_WHEN_READY)
                it.seekTo(savedInstanceState.getLong(PLAYER_CURRENT_POS))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        player?.let {
            outState.putLong(PLAYER_CURRENT_POS, max(0, it.currentPosition))
            outState.putBoolean(PLAYER_WHEN_READY, it.playWhenReady)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        if(newConfig!!.orientation == Configuration.ORIENTATION_LANDSCAPE){
            topAdBanner.visibility = View.GONE
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            topAdBanner.visibility = View.VISIBLE
        }
    }

    companion object {
        const val KEY_URI = "content_uri_string"
        const val PRE_AD = "live_tv_PreAdUrl"
        const val MID_AD = "live_tv_Mid_AdUrl"
        const val INTERSTITAL_TIME = "videoplay_duration_for_interstital"
        const val PLAYER_CURRENT_POS = "player_current_posotion"
        const val PLAYER_WHEN_READY = "player_when_ready"
    }
}