package com.exact.twitch.model.chat

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LiveChatMessage(
        val id: String,
        val userId: Int,
        val userType: String,
        val displayName: String,
        val roomId: String,
        val timestamp: Long,
        override val userName: String,
        override val message: String,
        override var color: String?,
        override val emotes: List<Emote>?,
        override val badges: List<Badge>?,
        override val subscriberBadge: SubscriberBadge?) : ChatMessage, Parcelable