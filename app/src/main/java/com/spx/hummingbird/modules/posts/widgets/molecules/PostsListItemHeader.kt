package com.spx.hummingbird.modules.posts.widgets.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.spx.hummingbird.modules.posts.model.PhotoPostsItem

@Composable
fun PostsListItemHeader(item: PhotoPostsItem) {
    Row(
        Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(item)
        AuthorInfo(item)
        Spacer(modifier = Modifier.weight(1f))
        FollowButton(item)
    }
}

@Composable
fun AuthorInfo(item: PhotoPostsItem) {
    Column(Modifier.padding(top = 0.dp)) {
        Text(
            text = "${item.username}",
            style = MaterialTheme.typography.labelLarge,
        )
        Row {
            Text(
                "${item.userTitle}",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF015880),
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                "${item.time}",
                style = MaterialTheme.typography.labelSmall,
            )
        }

    }
}

@Composable
fun Avatar(item: PhotoPostsItem) {
    val avatarUrl = item.headPic
    Box(
        modifier = Modifier
            .padding(end = 12.dp)
            .size(48.dp)
            .clip(CircleShape)
            .border(1.dp, Color.White, CircleShape)
            .clickable { /* 点击事件 */ }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatarUrl)
                .crossfade(true)
                .build(),
            contentDescription = "User Avatar",
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun FollowButton(item: PhotoPostsItem) {
    Row(
        modifier = Modifier
            .width(76.dp)
            .height(32.dp)
            .clip(MaterialTheme.shapes.large)
            .background(Color.Blue)
            .clickable {
                // 点击事件处理
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "+",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 2.dp)
        )
        Text(
            text = "关注",
            style = MaterialTheme.typography.labelMedium,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 2.dp)
        )
    }
}