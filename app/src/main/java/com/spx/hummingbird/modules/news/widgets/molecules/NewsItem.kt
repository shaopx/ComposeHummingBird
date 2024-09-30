package com.spx.hummingbird.modules.news.widgets.molecules


import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spx.hummingbird.modules.news.model.DocInfo

@Composable
fun NewsItem(item: DocInfo, modifier: Modifier= Modifier) {
    Box(modifier= modifier){
        when (item.docType) {
            1 -> NewsItemNormal(item)
            2 -> NewItem3Images(item)
            3 -> NewItemVideo(item)
            else -> Text("not support type")
        }
    }
}

