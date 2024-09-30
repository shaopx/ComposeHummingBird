package com.spx.hummingbird.modules.news.datasource

import com.spx.hummingbird.modules.news.model.DocDetailInfoResult
import com.spx.hummingbird.modules.news.model.NewsResult
import com.spx.hummingbird.modules.news.model.VideoNewsDetailResult

interface NewsDataSource {
    suspend fun getNews(channelId: String, page: Int): NewsResult
    suspend fun getVideoNewsDetail(docId: String): VideoNewsDetailResult
    suspend fun docDetail(docId: String): DocDetailInfoResult
}