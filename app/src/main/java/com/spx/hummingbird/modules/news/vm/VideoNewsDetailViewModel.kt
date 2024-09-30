package com.spx.hummingbird.modules.news.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spx.hummingbird.modules.news.model.VideoNewsDetailData
import com.spx.hummingbird.modules.news.repository.NewsDetailRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@HiltViewModel(assistedFactory = VideoNewsDetailViewModel.Factory::class)
class VideoNewsDetailViewModel @AssistedInject constructor(
    @Assisted val docId: String,
    private val respository: NewsDetailRepository,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(docId: String): VideoNewsDetailViewModel
    }

    private val _detailState: MutableStateFlow<VideoNewsDetailData?> =
        MutableStateFlow(null)
    val detailState: MutableStateFlow<VideoNewsDetailData?> get() = _detailState

    suspend fun fetchDetail() {
        viewModelScope.launch {
            respository.getVideoNewsDetail(docId)
                .collect { it ->
                    _detailState.value = it.data
                }
        }
    }
}