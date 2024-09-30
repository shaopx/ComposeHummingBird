package com.spx.hummingbird.modules.posts.widgets.organisms


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.spx.hummingbird.modules.posts.model.PhotoPostsItem
import com.spx.hummingbird.modules.posts.vm.PostsViewModel
import com.spx.hummingbird.modules.posts.widgets.molecules.PostsListItem


@Composable
fun PostsList(forumId: String) {
    val viewModel =
        hiltViewModel<PostsViewModel, PostsViewModel.PostsViewModelFactory>(key = forumId) { factory ->
            factory.create(forumId)
        }
    LaunchedEffect(true) {
        viewModel.fetchPosts()
    }

    val postsPagingItems: LazyPagingItems<PhotoPostsItem> =
        viewModel.postListState.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.padding(top = 0.dp)) {
        items(postsPagingItems.itemCount) { index ->
            val item = postsPagingItems[index]!!
            PostsListItem(item = item)
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color(0xFFF3F3F3),
                thickness = 8.dp
            )
        }

        postsPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            Column {
                                CircularProgressIndicator()
                                Text(
                                    "加载中...",
                                    modifier = Modifier.padding(top = 12.dp),
                                )
                            }
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error =
                        postsPagingItems.loadState.refresh as LoadState.Error
                    item { Text("LoadState.Loading$error") }
                }

                loadState.append is LoadState.Loading -> {
                    item { Text("LoadState.append") }
                }

                loadState.append is LoadState.Error -> {
                    val error =
                        postsPagingItems.loadState.append as LoadState.Error
                    item { Text("LoadState.Error$error") }
                }
            }
        }
    }

}