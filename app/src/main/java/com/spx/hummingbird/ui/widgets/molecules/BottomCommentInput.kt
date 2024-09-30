package com.spx.hummingbird.ui.widgets.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spx.hummingbird.R
import com.spx.hummingbird.ui.theme.white3

@Preview
@Composable
fun BottomCommentInput(
    commentCount: String, commentTapAction: () -> Unit,
    isCollected: Boolean, collectTapAction: () -> Unit,
    shareTapAction: () -> Unit,
    modifier: Modifier= Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Input()
        Spacer(Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.comment), // 替换为你的评论图标资源ID
            contentDescription = "Comment Icon",
            modifier = Modifier
                .padding(start = 28.dp)
                .size(32.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.favorite), // 替换为你的收藏图标资源ID
            contentDescription = "Bookmark Icon",
            modifier = Modifier
                .padding(start = 32.dp)
                .size(32.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.share_arrow), // 替换为你的收藏图标资源ID
            contentDescription = "Bookmark Icon",
            modifier = Modifier
                .padding(start = 28.dp)
                .size(32.dp)
        )
    }
}

@Composable
fun Input() {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(white3),
    ) {
        Text(
            text = "请留下您的想法",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterStart)
        )
    }
}