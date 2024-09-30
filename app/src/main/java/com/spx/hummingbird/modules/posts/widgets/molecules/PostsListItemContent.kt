package com.spx.hummingbird.modules.posts.widgets.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.spx.hummingbird.modules.posts.model.PhotoPostsItem

@Composable
fun PostsListItemContent(item: PhotoPostsItem) {
    return when(item.picInfo?.size){
        4 -> PostsListItemContent4(item)
        1 -> PostsListItemContent1(item)
        else -> Text("not support type")
    }
}

@Composable
fun PostsListItemContent4(item: PhotoPostsItem) {
    Column {
        val height1= item.picInfo?.get(0)?.h?:190
        val height2= item.picInfo?.get(2)?.h?:190
        Row(
            Modifier.height(height1.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.picInfo?.get(0)?.url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "User Avatar",
                modifier = Modifier.weight(item.picInfo?.get(0)?.w?.toFloat()!!),
            )
            Spacer(modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.picInfo[1].url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "User Avatar",
                modifier = Modifier.weight(item.picInfo[1].w.toFloat()),
            )
        }
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
        )
        Row(
            Modifier.height(height2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.picInfo?.get(2)?.url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "User Avatar",
                modifier = Modifier.weight(item.picInfo?.get(2)?.w?.toFloat()!!),
            )
            Spacer(modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.picInfo?.get(3)?.url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "User Avatar",
                modifier = Modifier.weight(item.picInfo?.get(3)?.w?.toFloat()!!),
            )
        }
    }

}


@Composable
fun PostsListItemContent1(item: PhotoPostsItem) {
    Column {
        val height1= item.picInfo?.get(0)?.h?:190
        Row(
            Modifier.height(height1.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.picInfo?.get(0)?.url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "User Avatar",
                modifier = Modifier.weight(item.picInfo?.get(0)?.w?.toFloat()!!),
            )
        }
    }

}