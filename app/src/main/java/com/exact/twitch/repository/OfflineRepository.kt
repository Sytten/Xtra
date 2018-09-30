package com.exact.twitch.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.exact.twitch.db.VideosDao
import com.exact.twitch.model.OfflineVideo
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineRepository @Inject constructor(
        private val videosDao: VideosDao) {

    fun loadAll(): LiveData<PagedList<OfflineVideo>> =
            LivePagedListBuilder(videosDao.getAll(), 15).build()

    fun insert(video: OfflineVideo) {
        launch { videosDao.insert(video) }
    }

    fun delete(video: OfflineVideo) {
        launch { videosDao.delete(video) }
    }
}
