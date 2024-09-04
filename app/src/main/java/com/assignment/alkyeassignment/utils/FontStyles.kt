package com.assignment.alkyeassignment.utils

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.core.content.res.ResourcesCompat
import com.assignment.alkyeassignment.R
import com.assignment.alkyeassignment.utils.ComposeExtensions.fromIntToSp
import com.assignment.alkyeassignment.utils.ComposeExtensions.getColor
import com.assignment.alkyeassignment.utils.ComposeExtensions.getColors
import com.assignment.alkyeassignment.utils.ComposeExtensions.getCustomTextSize

object FontStyles {
    fun Context.getStrawfordBoldFont(): Typeface? {
        return ResourcesCompat.getFont(this, R.font.strawford_bold)
    }

    fun Context.getStrawfordRegularFont(): Typeface? {
        return ResourcesCompat.getFont(this, R.font.strawford_regular)
    }

    fun Context.getStrawfordMediumFont(): Typeface? {
        return ResourcesCompat.getFont(this, R.font.strawford_medium)
    }

    fun Context.getSciliaRegularFont(): Typeface? {
        return ResourcesCompat.getFont(this, R.font.scilia_regular)
    }

    fun Context.getSciliaRegularItalicFont(): Typeface? {
        return ResourcesCompat.getFont(this, R.font.scilla_regular_italic)
    }

    @Composable
    fun String.toDarkLight(nextLine:Boolean=false,fontSize:Int= com.intuit.sdp.R.dimen._11sdp): AnnotatedString {
        var final = SpannableStringBuilder()
        val strings = this.split("\n")
        strings.indices.forEach {
            val tempDesc = SpannableString(strings[it])
            if (it == 0) {
                tempDesc.setSpan(
                    ForegroundColorSpan(getColor(R.color.black)),
                    0,
                    tempDesc.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                tempDesc.setSpan(
                    StyleSpan(LocalContext.current.getStrawfordBoldFont()!!.style),
                    0,
                    tempDesc.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                tempDesc.setSpan(
                    AbsoluteSizeSpan(getCustomTextSize(fontSize).toInt()),
                    0,
                    tempDesc.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else {
                tempDesc.setSpan(
                    ForegroundColorSpan(getColor(R.color.black)),
                    0,
                    tempDesc.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                tempDesc.setSpan(
                    StyleSpan(LocalContext.current.getStrawfordRegularFont()!!.style),
                    0,
                    tempDesc.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                tempDesc.setSpan(
                    AbsoluteSizeSpan(getCustomTextSize(fontSize).toInt()),
                    0,
                    tempDesc.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                final.append(if(nextLine)"\n" else " ")
            }
            final.append(tempDesc)
        }
        return final.toAnnotatedString()
    }


    @Composable
    fun SpannableStringBuilder.toAnnotatedString(): AnnotatedString {
        val builder = AnnotatedString.Builder(this.toString())
        val copierContext = CopierContext(getColors(R.color.black))
        SpanCopier.entries.forEach { copier ->
            getSpans(0, length, copier.spanClass).forEach { span ->
                copier.copySpan(span, getSpanStart(span), getSpanEnd(span), builder, copierContext)
            }
        }
        return builder.toAnnotatedString()
    }

    private data class CopierContext(
        val primaryColor: Color,
    )

    private enum class SpanCopier {
        FOREGROUND_COLOR {
            override val spanClass = ForegroundColorSpan::class.java
            @Composable
            override fun copySpan(
                span: Any,
                start: Int,
                end: Int,
                destination: AnnotatedString.Builder,
                context: CopierContext
            ) {
                val colorSpan = span as ForegroundColorSpan
                destination.addStyle(
                    style = SpanStyle(color = Color(colorSpan.foregroundColor)),
                    start = start,
                    end = end,
                )
            }
        },
        SIZE {
            override val spanClass = AbsoluteSizeSpan::class.java
            @Composable
            override fun copySpan(
                span: Any,
                start: Int,
                end: Int,
                destination: AnnotatedString.Builder,
                context: CopierContext
            ) {
                val sizeSpan = span as AbsoluteSizeSpan
                destination.addStyle(
                    style = SpanStyle(fontSize = sizeSpan.size.fromIntToSp()),
                    start = start,
                    end = end,
                )
            }
        },
        STYLE {
            override val spanClass = StyleSpan::class.java
            @Composable
            override fun copySpan(
                span: Any,
                start: Int,
                end: Int,
                destination: AnnotatedString.Builder,
                context: CopierContext
            ) {
                val styleSpan = span as StyleSpan

                destination.addStyle(
                    style = when (styleSpan.style) {
                        LocalContext.current.getStrawfordBoldFont()!!.style -> SpanStyle(fontFamily = FontFamily(LocalContext.current.getStrawfordBoldFont()!!))
                        LocalContext.current.getStrawfordMediumFont()!!.style -> SpanStyle(fontFamily = FontFamily(LocalContext.current.getStrawfordMediumFont()!!))
                        else -> SpanStyle(fontFamily = FontFamily(LocalContext.current.getStrawfordRegularFont()!!))
                    },
                    start = start,
                    end = end,
                )
            }
        };

        abstract val spanClass: Class<out CharacterStyle>
        @Composable
        abstract fun copySpan(
            span: Any,
            start: Int,
            end: Int,
            destination: AnnotatedString.Builder,
            context: CopierContext
        )
    }
}