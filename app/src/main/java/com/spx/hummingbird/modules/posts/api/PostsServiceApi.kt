package com.spx.hummingbird.modules.posts.api

import com.spx.hummingbird.modules.posts.model.ForumInfoResult
import com.spx.hummingbird.modules.posts.model.PhotoPostsResult
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PostsServiceApi {
    @POST(value = "index.php?r=wxa/bbs/thread/thread-list")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun getPosts(
        @Body requestBody: RequestBody
    ): PhotoPostsResult

    @POST(value = "index.php?r=wxa/bbs/thread/forum-index")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun getForumInfo(
        @Body requestBody: RequestBody
    ): ForumInfoResult
}
