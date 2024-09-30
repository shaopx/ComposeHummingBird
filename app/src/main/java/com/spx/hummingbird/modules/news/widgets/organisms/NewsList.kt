package com.spx.hummingbird.modules.news.widgets.organisms

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.spx.hummingbird.LocalNavController
import com.spx.hummingbird.modules.news.model.DocInfo
import com.spx.hummingbird.modules.news.vm.NewsViewModel
import com.spx.hummingbird.modules.news.widgets.molecules.NewsItem
import com.spx.hummingbird.modules.news.widgets.molecules.NewsListFocusInfo
import com.spx.hummingbird.modules.news.widgets.molecules.NewsListTopInfo


@Composable
fun NewsList(channelId: String) {
    val viewModel =
        hiltViewModel<NewsViewModel, NewsViewModel.NewsViewModelFactory>(key = channelId) { factory ->
            factory.create(channelId)
        }
    LaunchedEffect(true) {
        viewModel.fetchNews()
    }

    val moviePagingItems: LazyPagingItems<DocInfo> =
        viewModel.docInfoListState.collectAsLazyPagingItems()
    val itemCount = moviePagingItems.itemCount
    val focusInfoState = viewModel.focusInfoList.collectAsState()
    val topInfoState = viewModel.topInfoList.collectAsState()

    LazyColumn(modifier = Modifier.padding(top = 0.dp)) {
        items(itemCount) { index ->
            if (channelId == "0" && index == 0) {
                NewsListFocusInfo(
                    Modifier.padding(top = 0.dp),
                    focusInfoState.value.filter { it.picUrl != null }) {
                }
                NewsListTopInfo(
                    Modifier.padding(vertical = 12.dp),
                    topInfoState.value
                )
            }
            val item = moviePagingItems[index]!!
            NewsItem(item = item)
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                thickness = 0.5.dp
            )
        }

        moviePagingItems.apply {
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
                        moviePagingItems.loadState.refresh as LoadState.Error
                    item { Text("LoadState.Loading$error") }
                }

                loadState.append is LoadState.Loading -> {
                    item { Text("LoadState.append") }
                }

                loadState.append is LoadState.Error -> {
                    val error =
                        moviePagingItems.loadState.append as LoadState.Error
                    item { Text("LoadState.Error$error") }
                }
            }
        }
    }
//    }

}

@Composable
fun PreviewNewsList() {
    MaterialTheme {
        NewsList("0")
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewExample() {
    PreviewNewsList()
}