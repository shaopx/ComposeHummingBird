@file:OptIn(ExperimentalMaterial3Api::class)

package com.spx.hummingbird.modules.news.widgets.pages

import android.annotation.SuppressLint
import android.content.Intent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.spx.hummingbird.modules.news.vm.NewsDetailViewModel
import com.spx.hummingbird.modules.news.vm.VideoNewsDetailViewModel
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

    Scaffold(
//        topBar = {
//            TopBarWithBackButton(modifier = Modifier.padding(top = 0.dp))
//        },
        bottomBar = {
            BottomCommentInput(commentCount = (docDetail?.commentCount ?: "0"),
                commentTapAction = {

                },
                isCollected = docDetail?.isCollect == 1,
                collectTapAction = {},
                shareTapAction = {})
        }
    ) { paddingValues ->
        Column {
            TopBarWithBackButton(modifier = Modifier.padding(top = paddingValues.calculateTopPadding()))
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                    ),
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
        }

    }
}

