package com.assignment.alkyeassignment.views.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.assignment.alkyeassignment.R
import com.assignment.alkyeassignment.utils.ComposeExtensions
import com.assignment.alkyeassignment.utils.ComposeExtensions.margin
import com.assignment.alkyeassignment.utils.ComposeExtensions.setCustomSize

object SplashUI {
    @Composable
    fun SplashDesign() {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(ComposeExtensions.getColors(color = R.color.screen_background))) {
            Image(
                modifier = Modifier
                    .setCustomSize(dim = com.intuit.sdp.R.dimen._105sdp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.alkye_logo),
                contentDescription = null
            )
        }
    }
}