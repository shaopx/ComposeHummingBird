package com.spx.hummingbird.modules.posts.repository.pagesource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.spx.hummingbird.modules.news.datasource.NewsDataSource
import com.spx.hummingbird.modules.news.model.ChannelInfo
import com.spx.hummingbird.modules.news.model.DocInfo
import com.spx.hummingbird.modules.news.model.FocusInfo
import com.spx.hummingbird.modules.news.model.TopInfo
import com.spx.hummingbird.modules.posts.datasource.PostsDataSource
import com.spx.hummingbird.modules.posts.model.PhotoPostsItem
import com.spx.hummingbird.network.ResultWrapper
import com.spx.hummingbird.network.safeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.IOException
import javax.inject.Inject

class PostsPageSource @Inject constructor(
    private val forumId: String,
    private val postsDataSource: PostsDataSource,
) : PagingSource<Int, PhotoPostsItem>() {

    private val _channelList = MutableStateFlow<List<ChannelInfo>>(emptyList())
    val channelInfoList: StateFlow<List<ChannelInfo>> = _channelList


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoPostsItem> {
        return try {
            val currentPage = params.key ?: 1
            val resultWrapper = safeApiCall {
                postsDataSource.getPosts(
                    forumId = forumId,
                    page = currentPage,
                )
            }
            val newList = when (resultWrapper) {
                is ResultWrapper.NetworkError -> arrayListOf()
                is ResultWrapper.GenericError -> arrayListOf()
                is ResultWrapper.Success -> {
                    val data = resultWrapper.value.data
                    data.list
                }
            }

            LoadResult.Page(
                data = newList,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (newList.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoPostsItem>): Int? {
        return state.anchorPosition
    }

}