package com.spx.hummingbird.modules.news.repository

import com.spx.hummingbird.modules.news.model.DocDetailInfoResult
import com.spx.hummingbird.modules.news.model.VideoNewsDetailResult
import kotlinx.coroutines.flow.Flow

interface NewsDetailRepository {
    suspend fun getVideoNewsDetail(channelId: String): Flow<VideoNewsDetailResult>
    suspend fun getDocDetail(docInd: String): Flow<DocDetailInfoResult>
}