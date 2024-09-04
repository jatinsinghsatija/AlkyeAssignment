package com.assignment.alkyeassignment.views.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import com.assignment.alkyeassignment.R
import com.assignment.alkyeassignment.utils.ComposeExtensions
import com.assignment.alkyeassignment.utils.ComposeExtensions.FirstCapText
import com.assignment.alkyeassignment.utils.ComposeExtensions.getColors
import com.assignment.alkyeassignment.utils.ComposeExtensions.margin
import com.assignment.alkyeassignment.utils.ComposeExtensions.setCustomSize
import com.assignment.alkyeassignment.utils.FontStyles.getStrawfordBoldFont
import com.assignment.alkyeassignment.utils.FontStyles.getStrawfordMediumFont
import com.assignment.alkyeassignment.utils.FontStyles.getStrawfordRegularFont
import com.assignment.alkyeassignment.utils.FontStyles.toDarkLight
import com.assignment.alkyeassignment.utils.Utility
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object DetailsUI {
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun DetailsDesign(onDetailsNavigate: () -> Unit, onBack: () -> Unit) {
        val tabTitles = Utility.tabArr
        val pagerState = rememberPagerState(initialPage = 0)
        val tabState = rememberPagerState(initialPage = 0)
        val currentScroll = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(pagerState.currentPage) {
            if (currentScroll.value) {
                currentScroll.value = false
            } else {
                currentScroll.value = true
                tabState.animateScrollToPage(pagerState.currentPage)
            }

        }
        LaunchedEffect(tabState.currentPage) {
            if (currentScroll.value) {
                currentScroll.value = false
            } else {
                currentScroll.value = true
                pagerState.animateScrollToPage(tabState.currentPage)
            }

        }

        val coroutineScope = rememberCoroutineScope()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(getColors(R.color.screen_background))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(top = com.intuit.sdp.R.dimen._20sdp)
                    .margin(com.intuit.sdp.R.dimen._15sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .setCustomSize(dim = com.intuit.sdp.R.dimen._35sdp)
                        .background(
                            Color.Black,
                            CircleShape
                        )
                        .clip(CircleShape)
                        .clickable {
                            onBack.invoke()
                        }
                        .margin(com.intuit.sdp.R.dimen._7sdp),
                    painter = painterResource(id = R.drawable.back),
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier
                        .setCustomSize(dim = com.intuit.sdp.R.dimen._35sdp),
                    painter = painterResource(id = R.drawable.alkye_logo),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(com.intuit.sdp.R.dimen._5sdp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.margin(com.intuit.sdp.R.dimen._10sdp),
                    text = "Step Into Tomorrow:\nExploring Spatial Computing’s Impact On Industries And The Metaverse!".toDarkLight(
                        true,
                        com.intuit.sdp.R.dimen._20sdp
                    ),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .margin(
                            horizontal = com.intuit.sdp.R.dimen._5sdp,
                            vertical = com.intuit.sdp.R.dimen._10sdp
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .margin(com.intuit.sdp.R.dimen._5sdp)
                    ) {
                        Text(
                            text = "Type",
                            style = TextStyle(
                                fontFamily = LocalContext.current.getStrawfordBoldFont()
                                    ?.let { FontFamily(it) },
                                color = getColors(R.color.light_grey),
                                fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._11sdp),
                            ),
                        )
                        Text(
                            modifier = Modifier
                                .margin(
                                    top = com.intuit.sdp.R.dimen._3sdp
                                ),
                            text = "Article",
                            style = TextStyle(
                                fontFamily = LocalContext.current.getStrawfordBoldFont()
                                    ?.let { FontFamily(it) },
                                color = Color.Black,
                                fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._15sdp),
                            ),
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .margin(com.intuit.sdp.R.dimen._5sdp)
                    ) {
                        Text(
                            text = "Category",
                            style = TextStyle(
                                fontFamily = LocalContext.current.getStrawfordBoldFont()
                                    ?.let { FontFamily(it) },
                                color = getColors(R.color.light_grey),
                                fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._11sdp),
                            ),
                        )
                        Text(
                            modifier = Modifier
                                .margin(
                                    top = com.intuit.sdp.R.dimen._3sdp
                                ),
                            text = "Technology",
                            style = TextStyle(
                                fontFamily = LocalContext.current.getStrawfordBoldFont()
                                    ?.let { FontFamily(it) },
                                color = Color.Black,
                                fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._15sdp),
                            ),
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .margin(com.intuit.sdp.R.dimen._5sdp)
                    ) {
                        Text(
                            text = "Date",
                            style = TextStyle(
                                fontFamily = LocalContext.current.getStrawfordBoldFont()
                                    ?.let { FontFamily(it) },
                                color = getColors(R.color.light_grey),
                                fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._11sdp),
                            ),
                        )
                        Text(
                            modifier = Modifier
                                .margin(
                                    top = com.intuit.sdp.R.dimen._3sdp
                                ),
                            text = "26 Feb 2024",
                            style = TextStyle(
                                fontFamily = LocalContext.current.getStrawfordBoldFont()
                                    ?.let { FontFamily(it) },
                                color = Color.Black,
                                fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._15sdp),
                            ),
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .margin(com.intuit.sdp.R.dimen._10sdp)
                        .fillMaxWidth()
                        .height(setCustomSize(dim = com.intuit.sdp.R.dimen._250sdp))
                        .clip(RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp)))
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.alkye_demo_img),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        modifier = Modifier
                            .margin(com.intuit.sdp.R.dimen._15sdp)
                            .setCustomSize(dim = com.intuit.sdp.R.dimen._30sdp)
                            .background(
                                Color.Black,
                                CircleShape
                            )
                            .margin(com.intuit.sdp.R.dimen._7sdp),
                        painter = painterResource(id = R.drawable.bookmark),
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .margin(com.intuit.sdp.R.dimen._15sdp)
                            .align(Alignment.TopEnd)
                            .setCustomSize(dim = com.intuit.sdp.R.dimen._30sdp)
                            .background(
                                Color.Black,
                                CircleShape
                            )
                            .margin(com.intuit.sdp.R.dimen._7sdp),
                        colorFilter = ColorFilter.tint(Color.White),
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = null
                    )
                }
                /*TabRow(
                    containerColor = Color.Transparent,
                    selectedTabIndex = pagerState.currentPage % tabTitles.size,
                    contentColor = Color.Transparent,
                    indicator = {}
                ) {
                    tabTitles.forEachIndexed { index, title ->

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .margin(
                                    vertical = com.intuit.sdp.R.dimen._20sdp,
                                    horizontal = com.intuit.sdp.R.dimen._10sdp
                                ).clickable {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                            text = title.first,
                            style = TextStyle(
                                fontFamily = LocalContext.current.getStrawfordBoldFont()
                                    ?.let { FontFamily(it) },
                                color = if((pagerState.currentPage % tabTitles.size) == index)Color.Black else getColors(R.color.light_grey),
                                fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._15sdp),
                            ),
                        )
                        *//*Tab(
                            text = { Text(title.first) },
                            selected = (pagerState.currentPage % tabTitles.size) == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                        )*//*
                    }
                }*/

                HorizontalPager(
                    count = tabTitles.size,
                    state = tabState,
                    contentPadding = PaddingValues(
                        start = setCustomSize(dim = com.intuit.sdp.R.dimen._80sdp),
                        end = setCustomSize(dim = com.intuit.sdp.R.dimen._80sdp)
                    )
                ) { page ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .margin(vertical = com.intuit.sdp.R.dimen._10sdp)
                    ) {
                        Text(
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.Center),
                            textAlign = TextAlign.Center,
                            text = tabTitles[page].first,
                            style = TextStyle(
                                fontFamily = LocalContext.current.getStrawfordBoldFont()
                                    ?.let { FontFamily(it) },
                                color = if ((tabState.currentPage % tabTitles.size) == page) Color.Black else getColors(
                                    R.color.light_grey
                                ),
                                fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._13sdp),
                            ),
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(setCustomSize(dim = com.intuit.sdp.R.dimen._3sdp))
                        .background(
                            getColors(
                                color = R.color.line_grey
                            )
                        )
                )
                HorizontalPager(
                    count = tabTitles.size,
                    state = pagerState
                ) { page ->
                    PagerItem(tabTitles[page].second)
                }

                Text(
                    modifier = Modifier
                        .margin(
                            vertical = com.intuit.sdp.R.dimen._20sdp,
                            horizontal = com.intuit.sdp.R.dimen._10sdp
                        ),
                    text = "Related Articles",
                    style = TextStyle(
                        fontFamily = LocalContext.current.getStrawfordBoldFont()
                            ?.let { FontFamily(it) },
                        color = Color.Black,
                        fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._18sdp),
                    ),
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(4) {
                        RelatedItem(onDetailsNavigate)
                    }
                }

            }
        }
    }

    @Composable
    fun PagerItem(text: String) {
        Column(modifier = Modifier.fillMaxWidth()) {
            FirstCapText(
                text = text, biggerStyle = TextStyle(
                    fontFamily = LocalContext.current.getStrawfordRegularFont()
                        ?.let { FontFamily(it) },
                    color = Color.Black,
                    fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._51sdp),
                ), normalStyle = TextStyle(
                    fontFamily = LocalContext.current.getStrawfordRegularFont()
                        ?.let { FontFamily(it) },
                    color = Color.Black,
                    lineHeight = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._20sdp),
                    fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._15sdp),
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(top = com.intuit.sdp.R.dimen._5sdp)
            ) {
                Box(
                    modifier = Modifier
                        .margin(com.intuit.sdp.R.dimen._7sdp)
                        .height(setCustomSize(dim = com.intuit.sdp.R.dimen._40sdp))
                        .weight(1f)
                        .background(
                            Color.White,
                            RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                        )
                        .margin(horizontal = com.intuit.sdp.R.dimen._20sdp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Spatial Computing",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordBoldFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._11sdp),
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .margin(com.intuit.sdp.R.dimen._7sdp)
                        .height(setCustomSize(dim = com.intuit.sdp.R.dimen._40sdp))
                        .weight(1f)
                        .background(
                            Color.White,
                            RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                        )
                        .margin(horizontal = com.intuit.sdp.R.dimen._20sdp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Industrial Metaverse",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordBoldFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._11sdp),
                        )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(top = com.intuit.sdp.R.dimen._5sdp)
            ) {
                Box(
                    modifier = Modifier
                        .margin(com.intuit.sdp.R.dimen._7sdp)
                        .height(setCustomSize(dim = com.intuit.sdp.R.dimen._40sdp))
                        .weight(1f)
                        .background(
                            Color.White,
                            RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                        )
                        .margin(horizontal = com.intuit.sdp.R.dimen._20sdp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "AR in Retail",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordBoldFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._11sdp),
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .margin(com.intuit.sdp.R.dimen._7sdp)
                        .height(setCustomSize(dim = com.intuit.sdp.R.dimen._40sdp))
                        .weight(1f)
                        .background(
                            Color.White,
                            RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                        )
                        .margin(horizontal = com.intuit.sdp.R.dimen._20sdp)
                )
                {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Digital Interaction",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordBoldFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._11sdp),
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(top = com.intuit.sdp.R.dimen._5sdp)
            ) {
                Box(
                    modifier = Modifier
                        .margin(com.intuit.sdp.R.dimen._7sdp)
                        .height(setCustomSize(dim = com.intuit.sdp.R.dimen._40sdp))
                        .weight(1f)
                        .background(
                            Color.White,
                            RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                        )
                        .margin(horizontal = com.intuit.sdp.R.dimen._20sdp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Enterprise Tools",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordBoldFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._11sdp),
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .margin(com.intuit.sdp.R.dimen._7sdp)
                        .height(setCustomSize(dim = com.intuit.sdp.R.dimen._40sdp))
                        .weight(1f)
                        .background(
                            Color.White,
                            RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                        )
                        .margin(horizontal = com.intuit.sdp.R.dimen._20sdp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "AR/VR Technology",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordBoldFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._11sdp),
                        )
                    )
                }
            }
        }
    }

    @Composable
    fun RelatedItem(onDetailsNavigate: () -> Unit) {
        Column(
            modifier = Modifier
                .margin(com.intuit.sdp.R.dimen._10sdp)
                .width(setCustomSize(dim = com.intuit.sdp.R.dimen._200sdp))
                .background(
                    Color.White,
                    RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                )
                .clip(
                    RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                )
                .clickable {
                    onDetailsNavigate.invoke()
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(setCustomSize(dim = com.intuit.sdp.R.dimen._200sdp))
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.alkye_demo_img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Image(
                    modifier = Modifier
                        .margin(com.intuit.sdp.R.dimen._15sdp)
                        .setCustomSize(dim = com.intuit.sdp.R.dimen._30sdp)
                        .background(
                            Color.Black,
                            RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._5sdp))
                        )
                        .margin(com.intuit.sdp.R.dimen._7sdp),
                    painter = painterResource(id = R.drawable.youtube),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(com.intuit.sdp.R.dimen._15sdp)
            ) {


                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(
                        modifier = Modifier
                            .setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp)
                            .background(
                                Color.Black,
                                CircleShape
                            )

                    )
                    Text(
                        modifier = Modifier.margin(start = com.intuit.sdp.R.dimen._5sdp),
                        text = "technology",
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordMediumFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._8sdp),
                        ),
                    )
                }
                Text(
                    modifier = Modifier.margin(top = com.intuit.sdp.R.dimen._5sdp),
                    text = "Step Into Tomorrow:\nExploring Spatial Computing’s Impact On Industries And The Metaverse!".toDarkLight(),
                )

                Text(
                    modifier = Modifier.margin(top = com.intuit.sdp.R.dimen._20sdp),
                    text = "26 Feb 2024",
                    style = TextStyle(
                        fontFamily = LocalContext.current.getStrawfordMediumFont()
                            ?.let { FontFamily(it) },
                        color = getColors(color = R.color.light_grey),
                        fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._9sdp),
                    ),
                )
            }
        }
    }
}