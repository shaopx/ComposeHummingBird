package com.spx.hummingbird.modules.posts.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.spx.hummingbird.modules.posts.model.ForumInfoResult
import com.spx.hummingbird.modules.posts.model.PhotoPostsItem
import com.spx.hummingbird.modules.posts.repository.PostsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PostsViewModel.PostsViewModelFactory::class)
class PostsViewModel @AssistedInject constructor(
    @Assisted val forumId: String,
    val postsRepository: PostsRepository,
) : ViewModel() {

    @AssistedFactory
    interface PostsViewModelFactory {
        fun create(forumId: String): PostsViewModel
    }

//    private val postsRepository: PostsRepository by lazy {
//        PostsRepository(forumId, postsDataSource)
//    }

    private val _postsListState: MutableStateFlow<PagingData<PhotoPostsItem>> =
        MutableStateFlow(value = PagingData.empty())
    val postListState: MutableStateFlow<PagingData<PhotoPostsItem>> get() = _postsListState

    private val _forumInfo: MutableStateFlow<ForumInfoResult?> = MutableStateFlow(null)
    val forumInfo: MutableStateFlow<ForumInfoResult?> get() = _forumInfo

    suspend fun fetchPosts() {
        println("shaopx debug fetchPosts...forumId:$forumId")
        viewModelScope.launch {
            postsRepository.getPosts(forumId)
                .cachedIn(viewModelScope).collect {
                    _postsListState.value = it
                }
        }
    }

//    var channelInfoList =
//        postsRepository.pagingSource.channelInfoList.map { list ->
//            list.map {
//                it.channelId to it.channelName
//            }
//        }.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(),
//            initialValue = emptyList()
//        )

}