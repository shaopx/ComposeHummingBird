@file:OptIn(ExperimentalMaterial3Api::class)

package com.spx.hummingbird.modules.news.widgets.pages

import android.annotation.SuppressLint
import android.content.Intent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.spx.hummingbird.modules.news.vm.NewsDetailViewModel
import com.spx.hummingbird.ui.widgets.molecules.BottomCommentInput
import com.spx.hummingbird.ui.widgets.molecules.TopBarWithBackButton

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun TextNewsDetail(docId: String) {
    val context = LocalContext.current
    val viewModel =
        hiltViewModel<NewsDetailViewModel, NewsDetailViewModel.Factory>(
            key = docId
        ) { factory ->
            factory.create(docId)
        }
    val docDetail = viewModel.detailState.collectAsState().value
    LaunchedEffect(true) {
        viewModel.fetchDetail()
    }

    var bottomPaddingDp by remember { mutableStateOf(50.dp) }
    Scaffold(
        topBar = {
            TopBarWithBackButton(modifier = Modifier.padding(top = 0.dp))
        },
//        bottomBar = {
//            BottomCommentInput(commentCount = (docDetail?.commentCount ?: "0"),
//                commentTapAction = {
//
//                },
//                isCollected = docDetail?.isCollect == 1,
//                collectTapAction = {},
//                shareTapAction = {},
////                modifier = Modifier.padding(bottom = bottomPaddingDp)
//            )
//        }
    ) { paddingValues ->
        bottomPaddingDp = paddingValues.calculateBottomPadding()
        println("shaopx debug calculateBottomPadding:${paddingValues.calculateBottomPadding()}")
        println("shaopx debug calculateTopPadding:${paddingValues.calculateTopPadding()}")
        Column(modifier =  Modifier.padding(paddingValues)) {
//            TopBarWithBackButton(modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth().weight(1f),
                factory = {
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        webViewClient = object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                request: WebResourceRequest?
                            ): Boolean {
                                val url = request?.url.toString()
                                if (url.startsWith("http") || url.startsWith("https")) {
                                    view?.loadUrl(url)
                                } else {
                                    val intent =
                                        Intent(Intent.ACTION_VIEW, request?.url)
                                    context.startActivity(intent)
                                }
                                return true
                            }
                        }
                        loadUrl("https://apis.fengniao.com/index.php?r=asy/document/docDetail&docId=$docId&res=784*1701.8181818181818&pkg=com.fengniao.fengniaosheying&project=fnSmallProgram")
                    }
                }
            )
            BottomCommentInput(commentCount = (docDetail?.commentCount ?: "0"),
                commentTapAction = {

                },
                isCollected = docDetail?.isCollect == 1,
                collectTapAction = {},
                shareTapAction = {},
//                modifier = Modifier.padding(bottom = bottomPaddingDp)
            )
        }

    }
}

