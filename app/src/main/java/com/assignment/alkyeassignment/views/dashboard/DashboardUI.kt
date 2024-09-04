package com.assignment.alkyeassignment.views.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.assignment.alkyeassignment.R
import com.assignment.alkyeassignment.utils.ComposeExtensions
import com.assignment.alkyeassignment.utils.ComposeExtensions.DotsIndicator
import com.assignment.alkyeassignment.utils.ComposeExtensions.getColors
import com.assignment.alkyeassignment.utils.ComposeExtensions.margin
import com.assignment.alkyeassignment.utils.ComposeExtensions.setCustomSize
import com.assignment.alkyeassignment.utils.FontStyles.getStrawfordBoldFont
import com.assignment.alkyeassignment.utils.FontStyles.getStrawfordMediumFont
import com.assignment.alkyeassignment.utils.FontStyles.getStrawfordRegularFont
import com.assignment.alkyeassignment.utils.FontStyles.toDarkLight
import com.assignment.alkyeassignment.utils.Utility.getDashboardTabs
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

object DashboardUI {

    @Composable
    fun DashboardDesign(model: DashboarddViewModel,onDetailsNavigate:()->Unit) {
        val tab by remember {
            model.currentTab
        }
        val tabs = getDashboardTabs()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(getColors(R.color.screen_background))
        ) {
            Tabs(currentTab = tab,onDetailsNavigate)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(com.intuit.sdp.R.dimen._20sdp)
                    .shadow(
                        3.dp,
                        RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._30sdp))
                    )
                    .background(
                        Color.White,
                        RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._30sdp))
                    )
                    .margin(com.intuit.sdp.R.dimen._10sdp)
                    .align(Alignment.BottomCenter)
            ) {
                tabs.indices.forEach { index ->
                    Box(modifier = Modifier.weight(1f)) {
                        Image(
                            modifier = Modifier
                                .setCustomSize(dim = com.intuit.sdp.R.dimen._35sdp)
                                .background(
                                    if (index == tab) Color.Black else Color.White,
                                    CircleShape
                                )
                                .clip(CircleShape)
                                .clickable {
                                    model.currentTab.value = index
                                }
                                .margin(com.intuit.sdp.R.dimen._8sdp)
                                .align(Alignment.Center),
                            colorFilter = ColorFilter.tint(if (index == tab) Color.White else Color.Black),
                            painter = painterResource(id = tabs[index]),
                            contentDescription = null
                        )
                    }
                }

            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun Tabs(currentTab: Int,onDetailsNavigate:()->Unit) {
        if (currentTab == 0) Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(top = com.intuit.sdp.R.dimen._20sdp)
                    .margin(com.intuit.sdp.R.dimen._15sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .setCustomSize(dim = com.intuit.sdp.R.dimen._35sdp),
                    painter = painterResource(id = R.drawable.alkye_logo),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .background(
                            Color.White,
                            RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                        )
                        .margin(com.intuit.sdp.R.dimen._7sdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.margin(
                            end = com.intuit.sdp.R.dimen._20sdp
                        ),
                        text = "Search....",
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordMediumFont()
                                ?.let { FontFamily(it) },
                            color = getColors(color = R.color.light_grey),
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._10sdp),
                        ),
                    )
                    Image(
                        modifier = Modifier
                            .setCustomSize(dim = com.intuit.sdp.R.dimen._15sdp),
                        painter = painterResource(id = R.drawable.search),
                        colorFilter = ColorFilter.tint(getColors(color = R.color.light_grey)),
                        contentDescription = null
                    )
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    val pageCount = Int.MAX_VALUE
                    val realSize = 5
                    val middlePage = pageCount / 2
                    val pagerState =
                        rememberPagerState(initialPage = middlePage - (middlePage % realSize))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        HorizontalPager(
                            count = pageCount,
                            state = pagerState,
                            contentPadding = PaddingValues(end = setCustomSize(dim = com.intuit.sdp.R.dimen._80sdp))
                        ) { page ->
                            PagerItem(onDetailsNavigate)
                        }

                        Spacer(modifier = Modifier.margin(vertical = com.intuit.sdp.R.dimen._2sdp))


                        DotsIndicator(
                            totalDots = realSize,
                            selectedIndex = pagerState.currentPage % realSize
                        )
                    }
                }
                item {
                    Text(
                        modifier = Modifier
                            .margin(
                                vertical = com.intuit.sdp.R.dimen._20sdp,
                                horizontal = com.intuit.sdp.R.dimen._10sdp
                            ),
                        text = "Recent Articles",
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordBoldFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._18sdp),
                        ),
                    )
                }
                items(5) {
                    VideoItem(onDetailsNavigate)
                }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .margin(
                                    top = com.intuit.sdp.R.dimen._20sdp,
                                    bottom = com.intuit.sdp.R.dimen._30sdp
                                )
                                .background(
                                    Color.White,
                                    RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._15sdp))
                                )
                                .margin(
                                    vertical = com.intuit.sdp.R.dimen._7sdp,
                                    horizontal = com.intuit.sdp.R.dimen._60sdp
                                ),
                            text = "View All",
                            style = TextStyle(
                                fontFamily = LocalContext.current.getStrawfordMediumFont()
                                    ?.let { FontFamily(it) },
                                color = Color.Black,
                                fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._10sdp),
                            ),
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Black)
                                .margin(
                                    top = com.intuit.sdp.R.dimen._40sdp,
                                    bottom = com.intuit.sdp.R.dimen._80sdp
                                )
                        ) {
                            Text(
                                modifier = Modifier
                                    .margin(
                                        start = com.intuit.sdp.R.dimen._10sdp
                                    ),
                                text = "Social Connect",
                                style = TextStyle(
                                    fontFamily = LocalContext.current.getStrawfordBoldFont()
                                        ?.let { FontFamily(it) },
                                    color = Color.White,
                                    fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._20sdp),
                                ),
                            )
                            Text(
                                modifier = Modifier
                                    .margin(
                                        top = com.intuit.sdp.R.dimen._10sdp,
                                        start = com.intuit.sdp.R.dimen._10sdp
                                    ),
                                text = "Stay update with my latest post\non your favorite platforms",
                                style = TextStyle(
                                    fontFamily = LocalContext.current.getStrawfordMediumFont()
                                        ?.let { FontFamily(it) },
                                    color = Color.White,
                                    fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._12sdp),
                                ),
                            )
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .margin(top = com.intuit.sdp.R.dimen._10sdp)
                            ) {
                                items(4) {
                                    SocialItem(onDetailsNavigate)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun PagerItem(onDetailsNavigate:()->Unit) {
        Column(
            modifier = Modifier
                .margin(com.intuit.sdp.R.dimen._10sdp)
                .fillMaxWidth()
                .background(
                    Color.White,
                    RoundedCornerShape(
                        topStart = setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp),
                        bottomStart = setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp),
                        bottomEnd = setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp)
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topStart = setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp),
                        bottomStart = setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp),
                        bottomEnd = setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp)
                    )
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
                    painter = painterResource(id = R.drawable.star),
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

    @Composable
    fun VideoItem(onDetailsNavigate:()->Unit) {
        Row(
            modifier = Modifier
                .margin(com.intuit.sdp.R.dimen._10sdp)
                .fillMaxWidth()
                .height(setCustomSize(dim = com.intuit.sdp.R.dimen._300sdp))
                .background(
                    Color.White,
                    RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                )
                .clip(RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp)))
                .clickable {
                    onDetailsNavigate.invoke()
                }
        ) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.alkye_demo_img),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1.3f)
                    .margin(com.intuit.sdp.R.dimen._15sdp)
            ) {

                Image(
                    modifier = Modifier
                        .setCustomSize(dim = com.intuit.sdp.R.dimen._30sdp)
                        .background(
                            Color.Black,
                            RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._5sdp))
                        )
                        .margin(com.intuit.sdp.R.dimen._7sdp),
                    painter = painterResource(id = R.drawable.instagram),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .margin(top = com.intuit.sdp.R.dimen._20sdp)
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
                        text = "Step Into Tomorrow:",
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordBoldFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._14sdp),
                        ),
                    )
                    Text(
                        modifier = Modifier.margin(top = com.intuit.sdp.R.dimen._5sdp),
                        text = "Exploring Spatial Computing’s Impact On Industries And The Metaverse!",
                        style = TextStyle(
                            fontFamily = LocalContext.current.getStrawfordMediumFont()
                                ?.let { FontFamily(it) },
                            color = Color.Black,
                            fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._14sdp),
                        ),
                    )
                }

                Text(
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

    @Composable
    fun SocialItem(onDetailsNavigate:()->Unit) {
        Column(
            modifier = Modifier
                .margin(com.intuit.sdp.R.dimen._10sdp)
                .width(setCustomSize(dim = com.intuit.sdp.R.dimen._180sdp))
                .background(
                    Color.White,
                    RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp))
                )
                .clip(RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._20sdp)))
                .clickable {
                    onDetailsNavigate.invoke()
                }
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(setCustomSize(dim = com.intuit.sdp.R.dimen._250sdp))
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.alkye_demo_img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Image(
                    modifier = Modifier
                        .margin(com.intuit.sdp.R.dimen._20sdp)
                        .setCustomSize(dim = com.intuit.sdp.R.dimen._30sdp)
                        .background(
                            Color.Black,
                            RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._5sdp))
                        )
                        .margin(com.intuit.sdp.R.dimen._7sdp),
                    painter = painterResource(id = R.drawable.instagram),
                    contentDescription = null
                )
            }

            Text(
                modifier = Modifier
                    .margin(
                        com.intuit.sdp.R.dimen._20sdp
                    ),
                text = "The Ultimate Guide To Simplifying Your Routine With Generative AI Automation!",
                style = TextStyle(
                    fontFamily = LocalContext.current.getStrawfordMediumFont()
                        ?.let { FontFamily(it) },
                    color = Color.Black,
                    fontSize = ComposeExtensions.setCustomTextSize(com.intuit.sdp.R.dimen._14sdp),
                ),
            )
        }
    }
}