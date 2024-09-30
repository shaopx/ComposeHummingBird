package com.spx.hummingbird.modules.posts.datasource

import androidx.tracing.trace
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.spx.hummingbird.const.BUMMINGBIRD_BASE_URL
import com.spx.hummingbird.modules.posts.api.PostsServiceApi
import com.spx.hummingbird.modules.posts.model.ForumInfoResult
import com.spx.hummingbird.modules.posts.model.PhotoPostsResult
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import javax.inject.Inject

class PostsDataSourceImpl @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>
) : PostsDataSource {

    private val networkApi = trace("RetrofitNiaNetwork") {
        Retrofit.Builder()
            .baseUrl(BUMMINGBIRD_BASE_URL)
            // We use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(PostsServiceApi::class.java)
    }

    override suspend fun getPosts(forumId: String, page: Int): PhotoPostsResult {
        return networkApi.getPosts(createRequestBody(forumId, page))
    }

    override suspend fun getForumInfo(): ForumInfoResult {
        return networkApi.getForumInfo(createRequestBody("-1", 1))
    }

    private fun createRequestBody(forumId: String, page: Int): RequestBody {
        val params = mutableMapOf<String, Any?>(
            "forumid" to forumId,
            "userid" to "11508399",
            "page" to page,
            "pkg" to "com.fn.android",
            "os" to "Android",
            "oversion" to "12",
            "dmake" to "Xiaomi",
            "dmodel" to "Xiaomi",
            "aversion" to "5.2.1",
            "deviceid" to "umi",
            "isIos" to "false",
            "carrier" to "0",
            "project" to "fnSmallProgram"
        )
        val encodedParams =
            params.entries.joinToString("&") { "${it.key}=${it.value}" }
        return encodedParams.toRequestBody("application/x-www-form-urlencoded".toMediaTypeOrNull())
    }
}