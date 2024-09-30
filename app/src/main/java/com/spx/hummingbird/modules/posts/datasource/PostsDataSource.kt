package com.spx.hummingbird.modules.posts.datasource

import com.spx.hummingbird.modules.posts.model.ForumInfoResult
import com.spx.hummingbird.modules.posts.model.PhotoPostsResult

interface PostsDataSource {

    suspend fun getPosts(forumId: String, page:Int): PhotoPostsResult

    suspend fun getForumInfo(): ForumInfoResult
}