package com.spx.hummingbird.modules.news.widgets.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spx.hummingbird.R
import com.spx.hummingbird.modules.news.model.TopInfo


@Composable
fun NewsListTopInfo(
    modifier: Modifier,
    topInfoList: List<TopInfo>,
) {
    Row(Modifier.padding(top = 8.dp)) {
        Image(
            painterResource(R.drawable.topinfo_icon),
            "top info icon",
            Modifier
                .padding(start = 16.dp)
                .size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            modifier = Modifier.weight(1f)
        ) {
            topInfoList.forEachIndexed { _, topInfo ->
                TextWithDot(
                    text = topInfo.docTitle!!,
                )
            }
        }
    }
}

@Composable
fun TextWithDot(text: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(top = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(MaterialTheme.shapes.medium)
                .align(Alignment.CenterVertically)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}