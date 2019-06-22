package com.android.aman.exoplayer.api.data


import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("adbreak_message")
    val adbreakMessage: String?,
    @SerializedName("adbreak_poll_url")
    val adbreakPollUrl: String?,
    @SerializedName("admob_video_ad")
    val admobVideoAd: String?,
    @SerializedName("app_id")
    val appId: String?,
    @SerializedName("channel_name")
    val channelName: String?,
    @SerializedName("comscore")
    val comscore: String?,
    @SerializedName("comscore_pub_id")
    val comscorePubId: String?,
    @SerializedName("comscore_pub_secret")
    val comscorePubSecret: String?,
    @SerializedName("custom_player_banner_ad_fading_interval")
    val customPlayerBannerAdFadingInterval: String?,
    @SerializedName("2g_3g_youtube")
    val g3gYoutube: String?,
    @SerializedName("ga_analytics_id")
    val gaAnalyticsId: String?,
    @SerializedName("live_auto_rotate")
    val liveAutoRotate: String?,
    @SerializedName("live_event_title")
    val liveEventTitle: String?,
    @SerializedName("live_event_url")
    val liveEventUrl: String?,
    @SerializedName("liveevent_preroll_adtag")
    val liveeventPrerollAdtag: String?,
    @SerializedName("livetv_bottom_banner_display")
    val livetvBottomBannerDisplay: String?,
    @SerializedName("livetv_bottom_banner_id_admob")
    val livetvBottomBannerIdAdmob: String?,
    @SerializedName("livetv_bottom_banner_id_fb")
    val livetvBottomBannerIdFb: String?,
    @SerializedName("livetv_bottom_banner_preference")
    val livetvBottomBannerPreference: String?,
    @SerializedName("livetv_bottom_landscape_banner_display")
    val livetvBottomLandscapeBannerDisplay: String?,
    @SerializedName("livetv_bottom_landscape_banner_id_admob")
    val livetvBottomLandscapeBannerIdAdmob: String?,
    @SerializedName("livetv_bottom_landscape_banner_id_fb")
    val livetvBottomLandscapeBannerIdFb: String?,
    @SerializedName("livetv_bottom_landscape_banner_preference")
    val livetvBottomLandscapeBannerPreference: String?,
    @SerializedName("livetv_custom_player_banner_ad")
    val livetvCustomPlayerBannerAd: String?,
    @SerializedName("livetv_custom_player_banner_interval")
    val livetvCustomPlayerBannerInterval: String?,
    @SerializedName("livetv_interstitial_back")
    val livetvInterstitialBack: String?,
    @SerializedName("livetv_interstitial_id_admob")
    val livetvInterstitialIdAdmob: String?,
    @SerializedName("livetv_interstitial_id_fb")
    val livetvInterstitialIdFb: String?,
    @SerializedName("livetv_interstitial_preference")
    val livetvInterstitialPreference: String?,
    @SerializedName("livetv_interstitial_preroll")
    val livetvInterstitialPreroll: String?,
    @SerializedName("livetv_midroll_adschedule")
    val livetvMidrollAdschedule: List<Any?>?,
    @SerializedName("livetv_midroll_adtag")
    val livetvMidrollAdtag: List<String?>?,
    @SerializedName("livetv_midroll_enabled")
    val livetvMidrollEnabled: String?,
    @SerializedName("livetv_midroll_id_fb")
    val livetvMidrollIdFb: String?,
    @SerializedName("livetv_midroll_on_promo_end")
    val livetvMidrollOnPromoEnd: String?,
    @SerializedName("livetv_preroll_adtag")
    val livetvPrerollAdtag: String?,
    @SerializedName("livetv_preroll_fb_ad")
    val livetvPrerollFbAd: String?,
    @SerializedName("livetv_preroll_id_fb")
    val livetvPrerollIdFb: String?,
    @SerializedName("livetv_preroll_interstitial_id_admob")
    val livetvPrerollInterstitialIdAdmob: String?,
    @SerializedName("livetv_preroll_interstitial_id_fb")
    val livetvPrerollInterstitialIdFb: String?,
    @SerializedName("livetv_preroll_interstitial_preference")
    val livetvPrerollInterstitialPreference: String?,
    @SerializedName("livetv_promo_videos")
    val livetvPromoVideos: List<String?>?,
    @SerializedName("livetv_token_auth")
    val livetvTokenAuth: String?,
    @SerializedName("livetv_token_auth_api")
    val livetvTokenAuthApi: String?,
    @SerializedName("livetv_top_banner_display")
    val livetvTopBannerDisplay: String?,
    @SerializedName("livetv_top_banner_id_admob")
    val livetvTopBannerIdAdmob: String?,
    @SerializedName("livetv_top_banner_id_fb")
    val livetvTopBannerIdFb: String?,
    @SerializedName("livetv_top_banner_preference")
    val livetvTopBannerPreference: String?,
    @SerializedName("livetv_url")
    val livetvUrl: String?,
    @SerializedName("livetv_url_2g")
    val livetvUrl2g: String?,
    @SerializedName("livetv_url_3g")
    val livetvUrl3g: String?,
    @SerializedName("midroll_promos")
    val midrollPromos: String?,
    @SerializedName("network_error_message")
    val networkErrorMessage: String?,
    @SerializedName("preroll_adtag_timeout")
    val prerollAdtagTimeout: Int?,
    @SerializedName("radio_bottom_banner_display")
    val radioBottomBannerDisplay: String?,
    @SerializedName("radio_bottom_banner_id_admob")
    val radioBottomBannerIdAdmob: String?,
    @SerializedName("radio_bottom_banner_id_fb")
    val radioBottomBannerIdFb: String?,
    @SerializedName("radio_bottom_banner_preference")
    val radioBottomBannerPreference: String?,
    @SerializedName("radio_interstitial_back")
    val radioInterstitialBack: String?,
    @SerializedName("radio_interstitial_id_admob")
    val radioInterstitialIdAdmob: String?,
    @SerializedName("radio_interstitial_id_fb")
    val radioInterstitialIdFb: String?,
    @SerializedName("radio_interstitial_preference")
    val radioInterstitialPreference: String?,
    @SerializedName("radio_top_banner_display")
    val radioTopBannerDisplay: String?,
    @SerializedName("radio_top_banner_id_admob")
    val radioTopBannerIdAdmob: String?,
    @SerializedName("radio_top_banner_id_fb")
    val radioTopBannerIdFb: String?,
    @SerializedName("radio_top_banner_preference")
    val radioTopBannerPreference: String?,
    @SerializedName("radio_url")
    val radioUrl: String?,
    @SerializedName("stream_error_message")
    val streamErrorMessage: String?,
    @SerializedName("videoplay_duration_for_interstital")
    val videoplayDurationForInterstital: Int?,
    @SerializedName("vod_bottom_banner_display")
    val vodBottomBannerDisplay: String?,
    @SerializedName("vod_bottom_banner_id_admob")
    val vodBottomBannerIdAdmob: String?,
    @SerializedName("vod_bottom_banner_id_fb")
    val vodBottomBannerIdFb: String?,
    @SerializedName("vod_bottom_banner_preference")
    val vodBottomBannerPreference: String?,
    @SerializedName("vod_bottom_landscape_banner_display")
    val vodBottomLandscapeBannerDisplay: String?,
    @SerializedName("vod_custom_player_banner_ad")
    val vodCustomPlayerBannerAd: String?,
    @SerializedName("vod_custom_player_banner_interval")
    val vodCustomPlayerBannerInterval: String?,
    @SerializedName("vod_interstitial_back")
    val vodInterstitialBack: String?,
    @SerializedName("vod_interstitial_id_admob")
    val vodInterstitialIdAdmob: String?,
    @SerializedName("vod_interstitial_id_fb")
    val vodInterstitialIdFb: String?,
    @SerializedName("vod_interstitial_preference")
    val vodInterstitialPreference: String?,
    @SerializedName("vod_interstitial_preroll")
    val vodInterstitialPreroll: String?,
    @SerializedName("vod_landscape_banner_id_admob")
    val vodLandscapeBannerIdAdmob: String?,
    @SerializedName("vod_landscape_banner_id_fb")
    val vodLandscapeBannerIdFb: String?,
    @SerializedName("vod_landscape_banner_preference")
    val vodLandscapeBannerPreference: String?,
    @SerializedName("vod_midroll_adschedule")
    val vodMidrollAdschedule: Int?,
    @SerializedName("vod_midroll_adtag")
    val vodMidrollAdtag: String?,
    @SerializedName("vod_midroll_fb_ad")
    val vodMidrollFbAd: String?,
    @SerializedName("vod_midroll_id_fb")
    val vodMidrollIdFb: String?,
    @SerializedName("vod_preroll_adtag")
    val vodPrerollAdtag: String?,
    @SerializedName("vod_preroll_fb_ad")
    val vodPrerollFbAd: String?,
    @SerializedName("vod_preroll_id_fb")
    val vodPrerollIdFb: String?,
    @SerializedName("vod_preroll_interstitial_id_admob")
    val vodPrerollInterstitialIdAdmob: String?,
    @SerializedName("vod_preroll_interstitial_id_fb")
    val vodPrerollInterstitialIdFb: String?,
    @SerializedName("vod_preroll_interstitial_preference")
    val vodPrerollInterstitialPreference: String?,
    @SerializedName("vod_top_banner_display")
    val vodTopBannerDisplay: String?,
    @SerializedName("vod_top_banner_id_admob")
    val vodTopBannerIdAdmob: String?,
    @SerializedName("vod_top_banner_id_fb")
    val vodTopBannerIdFb: String?,
    @SerializedName("vod_top_banner_preference")
    val vodTopBannerPreference: String?,
    @SerializedName("webview_url")
    val webviewUrl: String?,
    @SerializedName("youtube_fallback")
    val youtubeFallback: String?,
    @SerializedName("youtube_id")
    val youtubeId: String?,
    @SerializedName("youtube_streaming")
    val youtubeStreaming: String?
)