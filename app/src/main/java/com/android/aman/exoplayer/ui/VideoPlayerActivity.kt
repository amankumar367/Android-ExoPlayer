package com.android.aman.exoplayer.ui

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.aman.exoplayer.R
import com.android.aman.exoplayer.api.data.ChannelList
import com.android.aman.exoplayer.api.repo.ChannelRepository
import com.android.aman.exoplayer.databinding.ActivityVideoPlayerBinding
import com.android.aman.exoplayer.ui.fragment.MediaPlayerFragment
import com.android.aman.exoplayer.ui.fragment.OnBackPressedI
import com.google.android.gms.ads.MobileAds
import java.io.Serializable

class VideoPlayerActivity : AppCompatActivity() {

    private val fragment = MediaPlayerFragment()
    private val channelRepository = ChannelRepository()
    private lateinit var viewModel: VideoPlayerViewModel
    private lateinit var databinding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setActionBarTitleCentre()
        setObserber()
        restoreFragment(savedInstanceState)
    }

    private fun init() {
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_video_player)
        viewModel = ViewModelProviders.of(this).get(VideoPlayerViewModel::class.java)
        viewModel.setRepositoryI(channelRepository)
        viewModel.getChhannel()

        MobileAds.initialize(this, R.string.YOUR_ADMOB_APP_ID.toString())
    }

    private fun setActionBarTitleCentre() {
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val textView = TextView(this)
        textView.text = title
        textView.textSize = 20f
        textView.setTypeface(null, Typeface.BOLD)
        textView.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        textView.gravity = Gravity.CENTER
        textView.setTextColor(resources.getColor(R.color.colorAccent))
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.customView = textView
    }

    private fun setObserber() {
        viewModel.state.observe(this, Observer<VideoPlayerState> { state ->
            setUiState(state)
        })
    }

    private fun setUiState(t: VideoPlayerState?) {
        databinding.state = t
        when{
            databinding.state!!.isSuccess -> {
                toast(databinding.state!!.message)
                showList(databinding.state!!.channelList!!)
            }
        }
    }

    private fun showList(t: ChannelList) {
        val liveTvUrl = arrayListOf(
            t.channels!![0]!!.livetvPromoVideos!![0]!!,
            t.channels[0]!!.livetvPromoVideos!![1]!!,
            t.channels[1]!!.livetvPromoVideos!![0]!!,
            t.channels[1]!!.livetvPromoVideos!![1]!!,
            t.channels[1]!!.livetvPromoVideos!![2]!!)

        val livetvPreAdUrl = arrayListOf(
            t.channels[0]!!.livetvPrerollAdtag,
            t.channels[1]!!.livetvPrerollAdtag)

        val livetvMidAdUrl = arrayListOf(
            t.channels[0]!!.livetvMidrollAdtag!![0]!!,
            t.channels[0]!!.livetvMidrollAdtag!![1]!!,
            t.channels[1]!!.livetvMidrollAdtag!![0]!!,
            t.channels[1]!!.livetvMidrollAdtag!![1]!!,
            t.channels[1]!!.livetvMidrollAdtag!![2]!!)

        val liveTvInterstitalAdTime = arrayListOf(
            t.channels[0]!!.videoplayDurationForInterstital,
            t.channels[1]!!.videoplayDurationForInterstital)


        startFragment(
            liveTvUrl,
            livetvPreAdUrl,
            livetvMidAdUrl,
            liveTvInterstitalAdTime)

        Log.d("ChannelList", "" + t.channels)
    }

    private fun startFragment(
        liveTvUrl: ArrayList<String>,
        livetvPreAdUrl: ArrayList<String?>,
        livetvMidAdUrl: ArrayList<String>,
        liveTvInterstitalAdTime: ArrayList<Int?>
    ) {
        val bundle = Bundle()
        bundle.putSerializable(MediaPlayerFragment.KEY_URI, liveTvUrl as Serializable)
        bundle.putSerializable(MediaPlayerFragment.PRE_AD, livetvPreAdUrl as Serializable)
        bundle.putSerializable(MediaPlayerFragment.MID_AD, livetvMidAdUrl as Serializable)
        bundle.putSerializable(MediaPlayerFragment.INTERSTITAL_TIME, liveTvInterstitalAdTime as Serializable)

        if(!fragment.isAdded) {
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.video_player, fragment).commit()
        }
    }

    override fun onBackPressed() {
        (fragment as? OnBackPressedI)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "MEDIA_PLAYER_FRAGMENT", fragment)
    }

    private fun restoreFragment(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            supportFragmentManager.getFragment(savedInstanceState, "MEDIA_PLAYER_FRAGMENT")
        }
    }

    private fun Activity.toast(string: String){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show()
    }
}
