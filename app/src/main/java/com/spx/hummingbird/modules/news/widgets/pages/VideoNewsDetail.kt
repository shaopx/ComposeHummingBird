package com.spx.hummingbird.modules.news.widgets.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import com.spx.hummingbird.modules.news.vm.VideoNewsDetailViewModel
import com.spx.hummingbird.modules.news.widgets.molecules.ExoPlayerView
import com.spx.hummingbird.modules.news.widgets.molecules.VideoRecommendItem
import com.spx.hummingbird.ui.widgets.molecules.BottomCommentInput
import com.spx.hummingbird.ui.widgets.molecules.TopBarWithBackButton

@UnstableApi
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun VideoNewsDetail(docId: String) {

    val viewModel =
        hiltViewModel<VideoNewsDetailViewModel, VideoNewsDetailViewModel.Factory>(
            key = docId
        ) { factory ->
            factory.create(docId)
        }
    val videoDetail = viewModel.detailState.collectAsState().value
    LaunchedEffect(true) {
        viewModel.fetchDetail()
    }

    var topPadding: Dp by remember { mutableStateOf(0.dp) }
    Scaffold { paddingValues ->
        topPadding = paddingValues.calculateTopPadding()
        if (videoDetail == null) {
            Text("正在加载")
        } else {
            val videoInfo = videoDetail.videoInfo;
            Box(
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    ExoPlayerView(videoInfo.videoUrl)
                    Text(
                        "${videoInfo.title}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                    )
                    Text(
                        "${videoInfo.views}次浏览  编辑:${videoInfo.author}  ${videoInfo.dateline}",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                        thickness = 0.5.dp
                    )
                    Spacer(Modifier.padding(bottom = 12.dp))
                    Text(
                        "精选视频",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 60.dp)
                    ) {
                        items(videoDetail.recommendInfo.size) { index ->
                            val recommendInfo =
                                videoDetail.recommendInfo[index]!!
                            VideoRecommendItem(item = recommendInfo)
                        }
                    }
//                Spacer(Modifier.weight(1f))

                }
                BottomCommentInput(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    commentCount = (videoDetail?.videoInfo?.countComment
                        ?: "0"),
                    commentTapAction = {

                    },
                    isCollected = videoDetail?.videoInfo?.isCollect == 1,
                    collectTapAction = {},
                    shareTapAction = {},
                )
            }

        }
    }
}