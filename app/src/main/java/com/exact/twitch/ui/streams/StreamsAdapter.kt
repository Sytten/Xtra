package com.exact.twitch.ui.streams

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.exact.twitch.R
import com.exact.twitch.databinding.FragmentStreamsListItemBinding
import com.exact.twitch.model.stream.Stream
import com.exact.twitch.ui.DataBoundPagedListAdapter

class StreamsAdapter(
        private val clickCallback: BaseStreamsFragment.OnStreamSelectedListener) : DataBoundPagedListAdapter<Stream, FragmentStreamsListItemBinding>(
        object : DiffUtil.ItemCallback<Stream>() {
            override fun areItemsTheSame(oldItem: Stream, newItem: Stream): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Stream, newItem: Stream): Boolean =
                    oldItem.viewers == newItem.viewers &&
                            oldItem.preview == newItem.preview &&
                            oldItem.game == newItem.game &&
                            oldItem.channel.status == newItem.channel.status
        }) {

    override fun createBinding(parent: ViewGroup): FragmentStreamsListItemBinding {
        val binding = DataBindingUtil.inflate<FragmentStreamsListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.fragment_streams_list_item,
                parent,
                false
        )
        binding.root.setOnClickListener { binding.stream?.let(clickCallback::startStream) }
        return binding
    }

    override fun bind(binding: FragmentStreamsListItemBinding, item: Stream?) {
        binding.stream = item
    }
}
