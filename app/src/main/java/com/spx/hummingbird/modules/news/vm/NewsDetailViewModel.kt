package com.spx.hummingbird.modules.news.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spx.hummingbird.modules.news.model.DocDetailInfo
import com.spx.hummingbird.modules.news.model.RootData
import com.spx.hummingbird.modules.news.repository.NewsDetailRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@HiltViewModel(assistedFactory = NewsDetailViewModel.Factory::class)
class NewsDetailViewModel @AssistedInject constructor(
    @Assisted val docId: String,
    private val respository: NewsDetailRepository,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(docId: String): NewsDetailViewModel
    }

    private val _detailState: MutableStateFlow<RootData?> =
        MutableStateFlow(null)
    val detailState: MutableStateFlow<RootData?> get() = _detailState

    suspend fun fetchDetail() {
        viewModelScope.launch {
            respository.getDocDetail(docId)
                .collect {
                    _detailState.value = it.data
                }
        }
    }
}