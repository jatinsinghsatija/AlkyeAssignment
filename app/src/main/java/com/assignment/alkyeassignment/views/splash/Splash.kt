package com.assignment.alkyeassignment.views.splash

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.assignment.alkyeassignment.ui.theme.AlkyeAssignmentTheme
import com.assignment.alkyeassignment.utils.Intents.gotoSignInSignUpAct
import com.assignment.alkyeassignment.utils.Utility
import com.assignment.alkyeassignment.views.splash.SplashUI.SplashDesign

class Splash : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        init()
        setContent {
            AlkyeAssignmentTheme {
                SplashDesign()
            }
        }
    }

    fun init() {
        Utility.startTimer(2000) {
            gotoSignInSignUpAct()
        }
    }
}


