package com.spx.hummingbird.modules.news.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.spx.hummingbird.modules.news.datasource.NewsDataSource
import com.spx.hummingbird.modules.news.model.DocInfo
import com.spx.hummingbird.modules.news.repository.NewsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = NewsViewModel.NewsViewModelFactory::class)
class NewsViewModel @AssistedInject constructor(
    @Assisted val channelId: String,
    private val newsDataSource: NewsDataSource,
) : ViewModel() {

    @AssistedFactory
    interface NewsViewModelFactory {
        fun create(channelId: String): NewsViewModel
    }

    private val newsRepository: NewsRepository by lazy {
        NewsRepository(channelId, newsDataSource)
    }

    private val _moviesState: MutableStateFlow<PagingData<DocInfo>> =
        MutableStateFlow(value = PagingData.empty())
    val docInfoListState: MutableStateFlow<PagingData<DocInfo>> get() = _moviesState

    suspend fun fetchNews() {
        viewModelScope.launch {
            newsRepository.commonPagingFlow
                .cachedIn(viewModelScope).collect { it ->
                    _moviesState.value = it
                }
        }
    }

    var channelInfoList =
        newsRepository.pagingSource.channelInfoList.map { list ->
            list.map {
                it.channelId to it.channelName
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )
    var focusInfoList =
        newsRepository.pagingSource.focusInfoList.map { list ->
            list
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )
    var topInfoList =
        newsRepository.pagingSource.topInfoList.map { list ->
            list
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

}