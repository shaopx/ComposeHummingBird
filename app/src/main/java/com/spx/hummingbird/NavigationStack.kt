package com.spx.hummingbird

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.spx.hummingbird.ui.widgets.Home

//@Composable
//fun NavigationStack() {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = Screen.Main.route) {
//        composable(route = Screen.Main.route) {
//            Home(navController = navController)
//        }
//        composable(
//            route = Screen.Detail.route + "?text={text}",
//            arguments = listOf(
//                navArgument("text") {
//                    type = NavType.StringType
//                    nullable = true
//                }
//            )
//        ) {
//            DetailScreen(text = it.arguments?.getString("text"))
//        }
//    }
//}