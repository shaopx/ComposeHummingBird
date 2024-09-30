package com.spx.hummingbird.modules.posts.widgets.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spx.hummingbird.modules.posts.vm.PostsPageViewModel
import com.spx.hummingbird.modules.posts.widgets.organisms.PostsList
import kotlinx.coroutines.launch

@Composable
fun PostsPage() {
    val coroutineScope = rememberCoroutineScope()
//    val viewModel =
//        hiltViewModel<PostsViewModel, PostsViewModel.NewsViewModelFactory>(key = "-1") { factory ->
//            factory.create("-1")
//        }
    val viewModel: PostsPageViewModel = hiltViewModel<PostsPageViewModel>()
    val forumInfoState = viewModel.forumInfo.collectAsState()
    LaunchedEffect(true) {
        viewModel.fetchData()
    }

    val tabs by remember(forumInfoState.value) {
        val result = forumInfoState.value
        if (result == null) {
            mutableStateOf(
                listOf(
                    "-1" to "精选",
                    "-2" to "关注",
                    "101" to "人像",
                    "-105" to "风光",
                )
            )
        } else {
            val data = result.data.index.map {
                it.forumid to it.name
            }
            mutableStateOf(data)
        }
    }
    val pagerState = rememberPagerState {
        tabs.size
    }

    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            contentColor = Color.Gray,
            indicator = { tabPositions ->
                TabRowDefaults.PrimaryIndicator(
                    width = 32.dp,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .fillMaxWidth()
                )
            }
        ) {
            tabs.forEachIndexed { index, (_, title) ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(title) },
                    modifier = Modifier.background(Color.White),
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            PostsList(tabs[page].first)
        }
    }
}


