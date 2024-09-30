package com.spx.hummingbird

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spx.hummingbird.ui.theme.HummingBirdTheme
import com.spx.hummingbird.modules.news.widgets.pages.TextNewsDetail
import com.spx.hummingbird.modules.news.widgets.pages.VideoNewsDetail
import com.spx.hummingbird.ui.widgets.Home
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HummingBirdTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    CompositionLocalProvider(LocalNavController provides navController) {
                        // App content with navigation
                        NavHost(navController = navController, startDestination = "home") {
                            composable("home") { Home() }
                            composable("textDetail/{docId}") { backStackEntry ->
                                val docId = backStackEntry.arguments?.getString("docId").orEmpty()
                                TextNewsDetail(docId)
                            }
                            composable("videoDetail/{docId}") { backStackEntry ->
                                val docId = backStackEntry.arguments?.getString("docId").orEmpty()
                                println("video clicked docId:$docId")
                                VideoNewsDetail(docId)
                            }
                        }
                    }
                }
            }
        }
    }
}

val LocalNavController =
    compositionLocalOf<NavController> { error("NavController not provided") }


sealed class Screen(val route: String) {
    data object Main : Screen("home")
    data object Detail : Screen("detail_screen")
}