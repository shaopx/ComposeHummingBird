package com.spx.hummingbird.modules.posts.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.spx.hummingbird.modules.posts.datasource.PostsDataSource
import com.spx.hummingbird.modules.posts.model.ForumInfoResult
import com.spx.hummingbird.modules.posts.model.PhotoPostsItem
import com.spx.hummingbird.modules.posts.repository.pagesource.PostsPageSource
import com.spx.hummingbird.network.ResultWrapper
import com.spx.hummingbird.network.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val dataSource: PostsDataSource
) : PostsRepository {

    override suspend fun getPosts(forumId: String): Flow<PagingData<PhotoPostsItem>> {
        println("shaopx debug repo getPosts...forumId:$forumId")
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                PostsPageSource(forumId, dataSource)
            }
        ).flow
    }

    override suspend fun getForumInfo(): Flow<ForumInfoResult> = flow{
        println("shaopx debug repo getForumInfo...")
        val resultWrapper = safeApiCall {
            dataSource.getForumInfo()
        }
        println("shaopx debug repo getForumInfo...resultWrapper:$resultWrapper")
       when (resultWrapper) {
            is ResultWrapper.NetworkError -> {}
            is ResultWrapper.GenericError -> {}
            is ResultWrapper.Success -> {
                emit(resultWrapper.value)
            }
        }
    }
}