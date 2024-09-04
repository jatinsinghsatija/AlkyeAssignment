package com.assignment.alkyeassignment.views.signin_signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.assignment.alkyeassignment.R
import com.assignment.alkyeassignment.utils.ComposeExtensions
import com.assignment.alkyeassignment.utils.ComposeExtensions.getColors
import com.assignment.alkyeassignment.utils.ComposeExtensions.margin
import com.assignment.alkyeassignment.utils.ComposeExtensions.setCustomSize
import com.assignment.alkyeassignment.utils.FontStyles
import com.assignment.alkyeassignment.utils.FontStyles.getSciliaRegularFont
import com.assignment.alkyeassignment.utils.FontStyles.getSciliaRegularItalicFont
import com.assignment.alkyeassignment.utils.FontStyles.getStrawfordBoldFont
import com.assignment.alkyeassignment.utils.FontStyles.getStrawfordMediumFont
import com.assignment.alkyeassignment.utils.FontStyles.getStrawfordRegularFont

object SigninSignupUI {
    @Composable
    fun SigninSignupDesign(onDashboardNavigate: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(getColors(color = R.color.screen_background))
                .margin(com.intuit.sdp.R.dimen._30sdp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Column(modifier = Modifier.weight(2f)) {
                Image(
                    modifier = Modifier
                        .setCustomSize(dim = com.intuit.sdp.R.dimen._35sdp),
                    painter = painterResource(id = R.drawable.alkye_logo),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.margin(
                        top = com.intuit.sdp.R.dimen._10sdp
                    ),
                    text = "Welcome",
                    style = TextStyle(
                        fontFamily = LocalContext.current.getStrawfordBoldFont()
                            ?.let { FontFamily(it) },
                        color = Color.Black,
                        fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._20sdp),
                    ),
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .margin(top = com.intuit.sdp.R.dimen._25sdp)
                ) {
                    Text(
                        text = "“",
                        style = TextStyle(
                            fontFamily = LocalContext.current.getSciliaRegularFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._56sdp),
                        ),
                    )
                    Text(
                        modifier = Modifier.margin(
                            top = com.intuit.sdp.R.dimen._15sdp
                        ),
                        text = "In a world of finite resources, the true measure of innovation lies not in its complexity, but in its capacity to simplify and empower the human mind.",
                        style = TextStyle(
                            fontFamily = LocalContext.current.getSciliaRegularItalicFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._16sdp),
                        ),
                    )
                }
            }
            Text(
                modifier = Modifier
                    .margin(top = com.intuit.sdp.R.dimen._40sdp)
                    .fillMaxWidth()
                    .background(
                        Color.Black,
                        RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp))
                    )
                    .clip(RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp)))
                    .clickable {
                        onDashboardNavigate.invoke()
                    }
                    .margin(com.intuit.sdp.R.dimen._15sdp),
                text = "Sign-up",
                style = TextStyle(
                    fontFamily = LocalContext.current.getStrawfordMediumFont()
                        ?.let { FontFamily(it) },
                    color = Color.White,
                    fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._16sdp),
                ),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .margin(top = com.intuit.sdp.R.dimen._10sdp)
                    .fillMaxWidth()
                    .border(
                        setCustomSize(dim = com.intuit.sdp.R.dimen._1sdp),
                        Color.Black,
                        RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp))
                    )
                    .clip(RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp)))
                    .clickable {
                        onDashboardNavigate.invoke()
                    }
                    .margin(com.intuit.sdp.R.dimen._15sdp),
                text = "Sign-in",
                style = TextStyle(
                    fontFamily = LocalContext.current.getStrawfordMediumFont()
                        ?.let { FontFamily(it) },
                    color = Color.Black,
                    fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._16sdp),
                ),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .margin(top = com.intuit.sdp.R.dimen._10sdp)
                    .fillMaxWidth()
                    .margin(com.intuit.sdp.R.dimen._10sdp),
                text = "Terms",
                style = TextStyle(
                    fontFamily = LocalContext.current.getStrawfordRegularFont()
                        ?.let { FontFamily(it) },
                    color = getColors(R.color.grey),
                    fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._16sdp),
                    textDecoration = TextDecoration.Underline
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}