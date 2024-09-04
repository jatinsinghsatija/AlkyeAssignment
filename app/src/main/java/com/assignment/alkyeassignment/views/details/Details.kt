package com.assignment.alkyeassignment.views.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.assignment.alkyeassignment.ui.theme.AlkyeAssignmentTheme
import com.assignment.alkyeassignment.utils.Intents.gotoDetailsAct
import com.assignment.alkyeassignment.utils.Utility
import com.assignment.alkyeassignment.utils.Utility.setWindowFlag
import com.assignment.alkyeassignment.views.details.DetailsUI.DetailsDesign
import com.assignment.alkyeassignment.views.signin_signup.SigninSignupUI.SigninSignupDesign

class Details : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = Color.TRANSPARENT
        setContent {
            AlkyeAssignmentTheme {
                DetailsDesign({ gotoDetailsAct() }, { finish() })
            }
        }
    }
}


