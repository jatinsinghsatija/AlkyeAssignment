package com.assignment.alkyeassignment.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.assignment.alkyeassignment.views.dashboard.Dashboard
import com.assignment.alkyeassignment.views.details.Details
import com.assignment.alkyeassignment.views.signin_signup.SigninSignup

object Intents {

    fun Activity.gotoSignInSignUpAct(){
        val intent = Intent(this, SigninSignup::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        this.finish()
    }
    fun Activity.gotoDashboardAct(){
        val intent = Intent(this, Dashboard::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    fun Activity.gotoDetailsAct(){
        val intent = Intent(this, Details::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}