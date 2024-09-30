package com.spx.hummingbird.modules.news.datasource

import androidx.tracing.trace
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.spx.hummingbird.modules.news.api.NewsServiceApi
import com.spx.hummingbird.modules.news.model.DocDetailInfoResult
import com.spx.hummingbird.modules.news.model.NewsResult
import com.spx.hummingbird.modules.news.model.VideoNewsDetailResult
import com.spx.hummingbird.network.createRequestBody
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject

private const val NIA_BASE_URL = "https://apis.fengniao.com/"


class NewsDataSourceImpl @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>
) : NewsDataSource {

    private val networkApi = trace("RetrofitNiaNetwork") {
        Retrofit.Builder()
            .baseUrl(NIA_BASE_URL)
            // We use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(NewsServiceApi::class.java)
    }

    override suspend fun getNews(channelId: String, page: Int): NewsResult {
        return networkApi.docLists(
            createRequestBody(
                mapOf(
                    "channelid" to channelId,
                    "page" to page,
                )
            )
        )
    }

    override suspend fun getVideoNewsDetail(docId: String): VideoNewsDetailResult {
        return networkApi.docVideoDetail(createRequestBody( mapOf(
            "docId" to docId,
        )))
    }

    override suspend fun docDetail(docId: String): DocDetailInfoResult {
        return networkApi.docDetail(createRequestBody( mapOf(
            "docId" to docId,
        )))
    }


}