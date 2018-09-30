package com.exact.twitch.ui.streams

import android.os.Bundle
import android.view.View
import com.exact.twitch.model.game.Game

class StreamsFragment : BaseStreamsFragment() {

    private var game: Game? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        game = arguments?.getParcelable("game")
    }

    override fun loadData(override: Boolean) {
        viewModel.loadStreams(game?.info?.name)
    }
}
