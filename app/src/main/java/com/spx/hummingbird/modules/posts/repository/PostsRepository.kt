package com.spx.hummingbird.modules.posts.repository

import androidx.paging.PagingData
import com.spx.hummingbird.modules.posts.model.ForumInfoResult
import com.spx.hummingbird.modules.posts.model.PhotoPostsItem
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    suspend fun getPosts(forumId: String): Flow<PagingData<PhotoPostsItem>>

    suspend fun getForumInfo(): Flow<ForumInfoResult>
}