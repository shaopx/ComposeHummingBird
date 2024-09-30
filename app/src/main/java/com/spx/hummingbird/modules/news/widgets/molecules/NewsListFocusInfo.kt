package com.spx.hummingbird.modules.news.widgets.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.spx.hummingbird.modules.news.model.FocusInfo

@Composable
fun NewsListFocusInfo(
    modifier: Modifier,
    focusInfoList: List<FocusInfo>,
    onItemClick: (String) -> Unit
) {
    val pagerState = rememberPagerState {
        focusInfoList.size
    }
    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .aspectRatio(16f / 9f)
            .fillMaxWidth(),
    ) { page ->
        ImageCard(
            imageUrl = focusInfoList[page].picUrl!!,
            onClick = { onItemClick(focusInfoList[page].jumpUrl!!) })
    }
}

@Composable
fun ImageCard(imageUrl: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(16f / 9f)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "focus image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Crop,
        )
    }
}