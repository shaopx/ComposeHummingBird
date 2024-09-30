package com.spx.hummingbird.modules.posts.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.spx.hummingbird.modules.posts.model.ForumInfoResult
import com.spx.hummingbird.modules.posts.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsPageViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
) : ViewModel() {

    private val _forumInfo: MutableStateFlow<ForumInfoResult?> =
        MutableStateFlow(null)
    val forumInfo: MutableStateFlow<ForumInfoResult?> get() = _forumInfo

    suspend fun fetchData() {
        println("shaopx debug vm fetchData...")
        viewModelScope.launch {
            postsRepository.getForumInfo()
                .collect {
                    _forumInfo.value = it
                }
        }
    }
}