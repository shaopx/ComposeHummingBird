package com.spx.hummingbird.modules.news.widgets.pages

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spx.hummingbird.modules.news.vm.NewsViewModel
import com.spx.hummingbird.modules.news.widgets.organisms.NewsList
import kotlinx.coroutines.launch

@Composable
fun NewsPage() {
    val coroutineScope = rememberCoroutineScope()
    val viewModel =
        hiltViewModel<NewsViewModel, NewsViewModel.NewsViewModelFactory>(key = "0") { factory ->
            factory.create("0")
        }
    val channelInfoState by viewModel.channelInfoList.collectAsState(
        initial = listOf(
            "0" to "头条",
            "999" to "视频",
        )
    )
    val tabs by remember(channelInfoState) {
        if (channelInfoState.isEmpty())
            mutableStateOf(
                listOf(
                    "0" to "头条",
                    "999" to "视频",
                )
            )
        else
            mutableStateOf(channelInfoState)
    }
    val pagerState = rememberPagerState {
        tabs.size
    }

    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
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
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            NewsList(tabs[page].first)
        }
    }
}


