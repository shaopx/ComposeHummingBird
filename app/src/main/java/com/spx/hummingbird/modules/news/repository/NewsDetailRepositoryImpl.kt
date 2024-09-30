package com.spx.hummingbird.modules.news.repository

import com.spx.hummingbird.modules.news.datasource.NewsDataSource
import com.spx.hummingbird.modules.news.model.DocDetailInfoResult
import com.spx.hummingbird.modules.news.model.VideoNewsDetailResult
import com.spx.hummingbird.network.ResultWrapper
import com.spx.hummingbird.network.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsDetailRepositoryImpl @Inject constructor(private val dataSource: NewsDataSource) :
    NewsDetailRepository {

    override suspend fun getVideoNewsDetail(docId: String): Flow<VideoNewsDetailResult> =
        flow {
            val resultWrapper = safeApiCall {
                dataSource.getVideoNewsDetail(docId)
            }
            when (resultWrapper) {
                is ResultWrapper.NetworkError -> {}
                is ResultWrapper.GenericError -> {}
                is ResultWrapper.Success -> {
                    emit(resultWrapper.value)
                }
            }
        }

    override suspend fun getDocDetail(docInd: String): Flow<DocDetailInfoResult> =
        flow {
            val resultWrapper = safeApiCall {
                dataSource.docDetail(docInd)
            }
            when (resultWrapper) {
                is ResultWrapper.NetworkError -> {}
                is ResultWrapper.GenericError -> {}
                is ResultWrapper.Success -> {
                    emit(resultWrapper.value)
                }
            }
        }
}