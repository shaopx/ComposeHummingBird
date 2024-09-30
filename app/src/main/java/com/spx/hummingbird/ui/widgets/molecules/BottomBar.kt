package com.spx.hummingbird.ui.widgets.molecules


import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spx.hummingbird.R
import com.spx.hummingbird.ui.theme.HummingBirdTheme


@Stable
fun Modifier.surface(
    shape: Shape,
    backgroundColor: Color,
    border: BorderStroke?,
    shadowElevation: Float,
) =
    this.then(
        if (shadowElevation > 0f) {
            Modifier.graphicsLayer(
                shadowElevation = shadowElevation,
                shape = shape,
                clip = false
            )
        } else {
            Modifier
        }
    )
        .then(if (border != null) Modifier.border(border, shape) else Modifier)
        .background(color = backgroundColor, shape = shape)
        .clip(shape)

@Composable
fun BottomBar(selected: Int, onSelectedChanged: (Int) -> Unit) {

    Surface(
        shadowElevation = 10.dp,
    ) {
        BottomAppBar(
            modifier = Modifier
                .requiredHeight(120.dp)
                .fillMaxWidth(),
        ) {
            Row {
                TabItem(
                    if (selected == 0) R.drawable.tab_news_selected else R.drawable.tab_news,
                    "资讯",
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            onSelectedChanged(0)
                        }
                )
                TabItem(
                    if (selected == 1) R.drawable.tab_photos_selected else R.drawable.tab_photos,
                    "帖子",
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            onSelectedChanged(1)
                        }
                )
                TabItem(
                    if (selected == 2) R.drawable.tab_post_selected else R.drawable.tab_post,
                    "发帖",
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            onSelectedChanged(2)
                        }
                )
                TabItem(
                    if (selected == 3) R.drawable.tab_profile_selected else R.drawable.tab_profile,
                    "我的",

                    Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            onSelectedChanged(3)
                        }
                )
            }
        }
    }
}

@Composable
fun TabItem(
    @DrawableRes iconId: Int,
    title: String,
    modifier: Modifier = Modifier,
    tint: Color = Color.Black
) {
    Column(
        modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(iconId), title, Modifier.size(24.dp))
        Text(title, fontSize = 11.sp, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
fun WeBottomBarPreview() {
    HummingBirdTheme {
        var selectedTab by remember { mutableIntStateOf(0) }
        BottomBar(selectedTab) { selectedTab = it }
    }
}

@Preview(showBackground = true)
@Composable
fun WeBottomBarPreviewDark() {
    HummingBirdTheme {
        var selectedTab by remember { mutableIntStateOf(0) }
        BottomBar(selectedTab) { selectedTab = it }
    }
}

@Preview(showBackground = true)
@Composable
fun WeBottomBarPreviewNewYear() {
    HummingBirdTheme {
        var selectedTab by remember { mutableIntStateOf(0) }
        BottomBar(selectedTab) { selectedTab = it }
    }
}

@Preview(showBackground = true)
@Composable
fun TabItemPreview() {
    TabItem(
        iconId = R.drawable.tab_news,
        title = "聊天",
        tint = MaterialTheme.colorScheme.surfaceTint
    )
}