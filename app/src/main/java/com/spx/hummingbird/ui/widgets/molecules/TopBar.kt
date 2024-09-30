package com.spx.hummingbird.ui.widgets.molecules

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spx.hummingbird.LocalNavController
import com.spx.hummingbird.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBackButton(
    modifier: Modifier = Modifier,
    iconTintColor: Color = Color.Black
) {
    val navController = LocalNavController.current
    Surface(modifier = modifier) {
        TopAppBar(
            modifier = Modifier
                .padding(horizontal = 16.dp),
//                .height(40.dp)
//                .padding(bottom = 8.dp),
            title = {
                Text(
                    text = "",
                )
            },
            navigationIcon = {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { navController.navigateUp() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "返回",
                        tint = iconTintColor,
                    )
                }
            },
//            windowInsets = TopAppBarDefaults.windowInsets.only(
//                WindowInsetsSides.Horizontal
//            ),
            colors = TopAppBarDefaults.topAppBarColors()
                .copy(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = Color.Transparent
                ),
        )
    }

}
