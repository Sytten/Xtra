package com.exact.twitch.ui.player.video

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

import com.exact.twitch.R
import com.exact.twitch.ui.VideoDownloadDialog
import com.exact.twitch.ui.fragment.RadioButtonDialogFragment
import com.exact.twitch.ui.player.BasePlayerFragment
import com.exact.twitch.util.FragmentUtils
import com.google.android.exoplayer2.source.hls.playlist.RenditionKey
import kotlinx.android.synthetic.main.fragment_player_video.*
import kotlinx.android.synthetic.main.player_video.*
import java.util.LinkedList

class VideoPlayerFragment : BasePlayerFragment(), RadioButtonDialogFragment.OnOptionSelectedListener, VideoDownloadDialog.OnDownloadClickListener {

    private companion object {
        const val TAG = "VideoPlayer"
    }

    private lateinit var viewModel: VideoPlayerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_player_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //        channelBtn.setOnClickListener(v -> channelListener.viewChannel(video.getChannel().getName()));
        //TODO morebtn
        settings.isEnabled = false
        download.isEnabled = false
        settings.setOnClickListener {
            LinkedList(viewModel.helper.qualities.value).also { list ->
                list.addFirst(getString(R.string.auto))
                FragmentUtils.showRadioButtonDialogFragment(requireActivity(), childFragmentManager, list, TAG)
            }
        }
        download.setOnClickListener { VideoDownloadDialog(this, viewModel.helper.qualities.value!!, viewModel.segments).show() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(VideoPlayerViewModel::class.java)
        playerView.player = viewModel.player
        viewModel.helper.qualities.observe(this, Observer {
            val loaded = it != null
            settings.isEnabled = loaded
            download.isEnabled = loaded
        })
        viewModel.helper.chatMessages.observe(this, Observer(chatView::submitList))
        viewModel.helper.newMessage.observe(this, Observer { chatView.notifyAdapter() })
        if (!viewModel.isInitialized()) {
            viewModel.video = arguments!!.getParcelable("video")!!
            viewModel.play()
        }
    }

    override fun onSelect(index: Int, text: CharSequence, tag: Int?) {
        viewModel.changeQuality(index, text.toString())
    }

    override fun onClick(quality: String, keys: List<RenditionKey>) {
        viewModel.download(quality, keys)
    }
}
