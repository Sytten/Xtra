package com.exact.twitch.api

import com.exact.twitch.model.chat.SubscriberBadgesResponse
import com.exact.twitch.model.clip.ClipStatusResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MiscApi {

    @GET("https://clips.twitch.tv/api/v2/clips/{slug}/status")
    fun getClipStatus(@Path("slug") slug: String): Single<ClipStatusResponse>

    @GET("https://badges.twitch.tv/v1/badges/channels/{channelId}/display")
    fun getSubscriberBadges(@Path("channelId") channelId: Int): Single<SubscriberBadgesResponse>
}