package com.spx.hummingbird.modules.news.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.spx.hummingbird.modules.news.datasource.NewsDataSource
import com.spx.hummingbird.modules.news.repository.pagesource.NewsPageSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class NewsRepository(
    channelId: String,
    dataSource: NewsDataSource
) {

    val pagingSource = NewsPageSource(channelId, dataSource)
    val commonPagingFlow = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 1,
            prefetchDistance = 3
        ),
        pagingSourceFactory = {  pagingSource}
    ).flow.flowOn(Dispatchers.IO)

}