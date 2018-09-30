package com.exact.twitch.model.chat

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Badge(
        @SerializedName("_id")
        val id: String,
        val version: String) : Parcelable
