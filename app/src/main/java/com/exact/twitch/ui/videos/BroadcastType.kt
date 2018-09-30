package com.exact.twitch.ui.videos

enum class BroadcastType(val value: String) {
    ALL("all"),
    ARCHIVE("archive"),
    HIGHLIGHT("highlight"),
    UPLOAD("upload");

    override fun toString() = value
}