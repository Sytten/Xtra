package com.exact.twitch.model

import com.google.gson.annotations.SerializedName

class PlaylistTokenResponse(
        val token: String,
        val sig: String,
        @SerializedName("mobile_restricted")
        val mobileRestricted: Boolean)