package com.assignment.alkyeassignment.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignment.alkyeassignment.R
import com.assignment.alkyeassignment.utils.FontStyles.getStrawfordRegularFont

object ComposeExtensions {

    @Composable
    fun setCustomTextSize(dim: Int): TextUnit {
        return dimensionResource(dim).dpToSp()
    }

    @Composable
    fun setCustomSize(dim: Int): Dp {
        return dimensionResource(dim)
    }

    @Composable
    fun getColors(color: Int): Color {
        return Color(LocalContext.current.resources.getColor(color))
    }


    @Composable
    fun FirstCapText(text:String,biggerStyle:TextStyle,normalStyle:TextStyle) {
        val firstChar=text.first().toString()
        val remainingText=text.drop(1)
        var visibleText by remember { mutableStateOf("") }
        var forthLineText by remember { mutableStateOf("") }
        forthLineText=remainingText.replace(visibleText,"")
        Column(modifier = Modifier
            .fillMaxWidth()
            .margin(com.intuit.sdp.R.dimen._10sdp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = firstChar,
                    style = biggerStyle
                )
                Text(
                    modifier=Modifier.margin(start= com.intuit.sdp.R.dimen._10sdp),
                    text = remainingText,
                    style = normalStyle,
                    overflow = TextOverflow.Clip,
                    maxLines = 3,
                    onTextLayout = { textLayoutResult: TextLayoutResult ->
                        // Extract visible text from TextLayoutResult
                        val maxWidthPx = textLayoutResult.size.width.toFloat()
                        val maxHeightPx = textLayoutResult.size.height.toFloat()

                        visibleText = fitTextToConstraints(
                            text = remainingText,
                            textLayoutResult = textLayoutResult,
                            maxWidthPx = maxWidthPx,
                            maxHeightPx = maxHeightPx
                        )
                    }
                )
            }
            Text(
                text = forthLineText,
                style = normalStyle
            )
        }

    }

    private fun fitTextToConstraints(
        text: String,
        textLayoutResult: TextLayoutResult,
        maxWidthPx: Float,
        maxHeightPx: Float
    ): String {
        var actualText=text
        var visibleText = StringBuilder("")
        var currentHeight = 0f
        val lineCount = textLayoutResult.lineCount
        val perLineHeight=maxHeightPx/lineCount

        // Use text layout lines

        for (i in 0 until lineCount) {
            val lineStart = textLayoutResult.getLineStart(i)
            val lineEnd = textLayoutResult.getLineEnd(i)
            val lineWidth = textLayoutResult.size.width

            if (lineWidth > maxWidthPx) {
                break
            }

            val lineText = actualText.substring(lineStart, lineEnd)
            if ((currentHeight + perLineHeight) > maxHeightPx) {
                break
            }

            currentHeight += perLineHeight
            visibleText.append(lineText)
            actualText.replace(lineText,"")
        }

        return visibleText.toString()
    }

    @Composable
    fun Modifier.setCustomSize(dim: Int): Modifier {
        return this.size(dimensionResource(dim))

    }

    @Composable
    fun Int.fromIntToDp(): Dp {
        val value = this
        return LocalDensity.current.run { value.toDp() }
    }

    @Composable
    fun Dp.dpToSp(): TextUnit {
        val dp = this
        return with(LocalDensity.current) { dp.toSp() }
    }

    @Composable
    fun Modifier.margin(
        top: Int? = null,
        bottom: Int? = null,
        start: Int? = null,
        end: Int? = null
    ): Modifier {
        return this.padding(
            top = top?.let { dimensionResource(it) } ?: 0.dp,
            bottom = bottom?.let { dimensionResource(it) } ?: 0.dp,
            start = start?.let { dimensionResource(it) } ?: 0.dp,
            end = end?.let { dimensionResource(it) } ?: 0.dp
        )
    }

    @Composable
    fun Int.fromIntToSp(): TextUnit {
        val px = this
        val sp: Float = px / LocalContext.current.resources.displayMetrics.scaledDensity
        return TextUnit(value = sp, type = TextUnitType.Sp)

    }

    @Composable
    fun Modifier.margin(all: Int? = null): Modifier {
        return this.padding(all?.let { dimensionResource(it) } ?: 0.dp)
    }

    @Composable
    fun getCustomTextSize(fontSize: Int): Float {
        return (LocalContext.current.resources.getDimensionPixelSize(fontSize).toFloat())
    }

    @Composable
    fun Modifier.margin(vertical: Int? = null, horizontal: Int? = null): Modifier {
        return this.padding(
            vertical = vertical?.let { dimensionResource(vertical) } ?: 0.dp,
            horizontal = horizontal?.let { dimensionResource(horizontal) } ?: 0.dp
        )
    }

    @Composable
    fun getColor(color: Int): Int {
        return LocalContext.current.resources.getColor(color)
    }

    @Composable
    fun DotsIndicator(
        totalDots: Int,
        selectedIndex: Int
    ) {

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .margin(horizontal = com.intuit.sdp.R.dimen._15sdp)
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically

        ) {

            items(totalDots) { index ->
                if (index == selectedIndex) {
                    Box(
                        modifier = Modifier
                            .size(
                                setCustomSize(dim = com.intuit.sdp.R.dimen._30sdp),
                                setCustomSize(dim = com.intuit.sdp.R.dimen._5sdp)
                            )
                            .setCustomSize(dim = com.intuit.sdp.R.dimen._6sdp)
                            .clip(RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._5sdp)))
                            .background(getColors(color = R.color.indicator_grey))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .setCustomSize(dim = com.intuit.sdp.R.dimen._5sdp)
                            .clip(CircleShape)
                            .background(getColors(color = R.color.indicator_grey))
                    )
                }

                if (index != totalDots - 1) {
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                }
            }
        }
    }

}