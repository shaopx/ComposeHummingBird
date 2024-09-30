package com.spx.hummingbird.modules.news.repository.pagesource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.spx.hummingbird.modules.news.datasource.NewsDataSource
import com.spx.hummingbird.modules.news.model.ChannelInfo
import com.spx.hummingbird.modules.news.model.DocInfo
import com.spx.hummingbird.modules.news.model.FocusInfo
import com.spx.hummingbird.modules.news.model.TopInfo
import com.spx.hummingbird.network.ResultWrapper
import com.spx.hummingbird.network.safeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.IOException
import javax.inject.Inject

class NewsPageSource @Inject constructor(
    private val channelId: String,
    private val newsDataSource: NewsDataSource,
) : PagingSource<Int, DocInfo>() {

    private val _channelList = MutableStateFlow<List<ChannelInfo>>(emptyList())
    val channelInfoList: StateFlow<List<ChannelInfo>> = _channelList

    private val _focusInfoList = MutableStateFlow<List<FocusInfo>>(emptyList())
    val focusInfoList: StateFlow<List<FocusInfo>> = _focusInfoList

    private val _topInfoList = MutableStateFlow<List<TopInfo>>(emptyList())
    val topInfoList: StateFlow<List<TopInfo>> = _topInfoList

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DocInfo> {
        return try {
            val currentPage = params.key ?: 1
            val resultWrapper = safeApiCall {
                newsDataSource.getNews(
                    channelId = channelId,
                    page = currentPage,
                )
            }
            val newList = when (resultWrapper) {
                is ResultWrapper.NetworkError -> arrayListOf()
                is ResultWrapper.GenericError -> arrayListOf()
                is ResultWrapper.Success -> {
                    val data = resultWrapper.value.data
                    _channelList.value =
                        data.channelInfo ?: _channelList.value
                    _topInfoList.value = data.topInfo ?: _topInfoList.value
                    _focusInfoList.value =
                        data.focusInfo ?: _focusInfoList.value
                    data.docLists ?: arrayListOf()
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

    override fun getRefreshKey(state: PagingState<Int, DocInfo>): Int? {
        return state.anchorPosition
    }

}