package com.spx.hummingbird.modules.news.api

import com.spx.hummingbird.modules.news.model.DocDetailInfoResult
import com.spx.hummingbird.modules.news.model.NewsResult
import com.spx.hummingbird.modules.news.model.VideoNewsDetailResult
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NewsServiceApi {
    @POST(value = "index.php?r=asy/document/docLists")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun docLists(
        @Body requestBody: RequestBody
    ): NewsResult

    @POST(value = "index.php?r=asy/document/docDetail")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun docDetail(
        @Body requestBody: RequestBody
    ): DocDetailInfoResult

    @POST(value = "index.php?r=asy/document/docVideoDetail")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun docVideoDetail(
        @Body requestBody: RequestBody
    ): VideoNewsDetailResult
}
