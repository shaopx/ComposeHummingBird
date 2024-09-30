package com.spx.hummingbird.modules.posts.widgets.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spx.hummingbird.modules.news.model.DocInfo
import com.spx.hummingbird.modules.news.widgets.molecules.NewItem3Images
import com.spx.hummingbird.modules.news.widgets.molecules.NewItemVideo
import com.spx.hummingbird.modules.news.widgets.molecules.NewsItemNormal
import com.spx.hummingbird.modules.posts.model.PhotoPostsItem

@Composable
fun PostsListItem(item: PhotoPostsItem) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        PostsListItemHeader(item)
        Text(
            "${item.title}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
        )
        PostsListItemContent(item)
    }
}
