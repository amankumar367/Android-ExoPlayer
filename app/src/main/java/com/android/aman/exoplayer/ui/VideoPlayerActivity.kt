package com.android.aman.exoplayer.ui

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.aman.exoplayer.R
import com.android.aman.exoplayer.api.data.ChannelList
import com.android.aman.exoplayer.api.repo.ChannelRepository
import com.android.aman.exoplayer.databinding.ActivityVideoPlayerBinding
import com.google.android.gms.ads.MobileAds
import java.io.Serializable

class VideoPlayerActivity : AppCompatActivity() {

    private var liveTvUrl : String? = null
    private val channelRepository = ChannelRepository()
    private lateinit var viewModel: VideoPlayerViewModel
    private lateinit var databinding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setActionBarTitleCentre()
        setObserber()
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
        viewModel.state.observe(this, object: Observer<VideoPlayerState>{
            override fun onChanged(t: VideoPlayerState?) {
                setUiState(t)
                if(t != null)
                    Log.d("ChannelList",""+ t.message)
                else
                    Log.d("ChannelList","" + t!!.message)
            }

        })

        viewModel.channelList.observe(this,
            Observer<ChannelList> { t ->
                if(t != null){
                    val liveTvUrl = arrayListOf(
                        t.channels!![0]!!.livetvPromoVideos!![0]!!,
                        t.channels[0]!!.livetvPromoVideos!![1]!!,
                        t.channels[1]!!.livetvPromoVideos!![0]!!,
                        t.channels[1]!!.livetvPromoVideos!![1]!!,
                        t.channels[1]!!.livetvPromoVideos!![2]!!)

                    val livetvPreAdUrl = arrayListOf(
                        t.channels[0]!!.livetvPrerollAdtag,
                        t.channels[1]!!.livetvPrerollAdtag)

                    val livetvTopAdUrl = arrayListOf(
                        t.channels[0]!!.livetvTopBannerIdAdmob,
                        t.channels[1]!!.livetvTopBannerIdAdmob
                    )

                    val livetvBottomAdUrl = arrayListOf(
                        t.channels[0]!!.livetvBottomLandscapeBannerIdAdmob,
                        t.channels[1]!!.livetvBottomLandscapeBannerIdAdmob
                    )

                    val livetvMidAdUrl = arrayListOf(
                        t.channels[0]!!.livetvMidrollAdtag!![0]!!,
                        t.channels[0]!!.livetvMidrollAdtag!![1]!!,
                        t.channels[1]!!.livetvMidrollAdtag!![0]!!,
                        t.channels[1]!!.livetvMidrollAdtag!![1]!!,
                        t.channels[1]!!.livetvMidrollAdtag!![2]!!)
                    startFragment(liveTvUrl)
                    Log.d("ChannelList",""+ t.channels)
                } else
                    Log.d("ChannelList","List is empty")
            })
    }

    private fun setUiState(t: VideoPlayerState?) {
        databinding.state = t
    }

    private fun startFragment(liveTvUrl: ArrayList<String>) {
        val fragment = MediaPlayerFragment()
        val bundle = Bundle()
        bundle.putSerializable(MediaPlayerFragment.KEY_URI, liveTvUrl as Serializable)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.video_player, fragment).commit()
    }

}
