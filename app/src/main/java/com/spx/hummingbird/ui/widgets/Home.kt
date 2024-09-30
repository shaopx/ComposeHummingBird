package com.spx.hummingbird.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.spx.hummingbird.modules.news.widgets.pages.NewsPage
import com.spx.hummingbird.modules.posts.widgets.pages.PostsPage
import com.spx.hummingbird.ui.widgets.molecules.BottomBar
import kotlinx.coroutines.launch


@Composable
fun Home(){
    val pagerState = rememberPagerState {
        4
    }
    val scope = rememberCoroutineScope()
    Scaffold(bottomBar = {
        // 将WeBottomBar放入bottomBar槽位
        BottomBar(pagerState.currentPage) { page ->
            // 点击页签后，在协程里翻页
            scope.launch {
                pagerState.animateScrollToPage(page)
            }
        }
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                // 每一页的内容
                when (page) {
                    0 -> NewsPage()
                    1 -> PostsPage()
                    2 -> Text(
                        "Page 3",
                        modifier = Modifier.fillMaxSize()
                    )

                    3 -> Text(
                        "Page 4",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}