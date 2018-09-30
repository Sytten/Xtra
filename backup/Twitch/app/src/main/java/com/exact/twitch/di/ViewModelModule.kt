package com.exact.twitch.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.exact.twitch.ui.player.clip.ClipPlayerViewModel
import com.exact.twitch.ui.clips.ClipsViewModel
import com.exact.twitch.ui.downloads.DownloadsViewModel
import com.exact.twitch.ui.games.GamesViewModel
import com.exact.twitch.ui.viewmodel.GenericViewModelFactory
import com.exact.twitch.ui.player.offline.OfflinePlayerViewModel
import com.exact.twitch.ui.player.stream.StreamPlayerViewModel
import com.exact.twitch.ui.streams.StreamsViewModel
import com.exact.twitch.ui.player.video.VideoPlayerViewModel
import com.exact.twitch.ui.videos.VideosViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GamesViewModel::class)
    abstract fun bindGamesViewModel(gamesViewModel: GamesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StreamsViewModel::class)
    abstract fun bindStreamsViewModel(streamListViewModel: StreamsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StreamPlayerViewModel::class)
    abstract fun bindStreamPlayerViewModel(streamPlayerViewModel: StreamPlayerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VideoPlayerViewModel::class)
    abstract fun bindVideoPlayerViewModel(videoPlayerViewModel: VideoPlayerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ClipPlayerViewModel::class)
    abstract fun bindClipPlayerViewModel(clipPlayerViewModel: ClipPlayerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OfflinePlayerViewModel::class)
    abstract fun bindOfflinePlayerViewModel(offlinePlayerViewModel: OfflinePlayerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VideosViewModel::class)
    abstract fun bindVideosViewModel(videosViewModel: VideosViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DownloadsViewModel::class)
    abstract fun bindDownloadsViewModel(downloadsViewModel: DownloadsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ClipsViewModel::class)
    abstract fun bindClipsViewModel(clipsViewModel: ClipsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: GenericViewModelFactory): ViewModelProvider.Factory
}
