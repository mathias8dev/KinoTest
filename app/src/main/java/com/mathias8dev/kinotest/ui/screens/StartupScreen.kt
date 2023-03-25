package com.mathias8dev.kinotest.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.mathias8dev.kinotest.ui.components.Center
import com.mathias8dev.kinotest.R
import com.mathias8dev.kinotest.domain.viewModel.StartupScreenViewModel
import com.mathias8dev.kinotest.ui.components.AppImage
import com.mathias8dev.kinotest.ui.components.PageIndicators
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StartupScreen(
    vm: StartupScreenViewModel = StartupScreenViewModel(),
    navigate: () -> Unit = {}
) {

    val pagerState = rememberPagerState()
    val pageCount = 3
    val coroutineScope = rememberCoroutineScope()

    Column {
        Box(modifier = Modifier.weight(2F)) {
            HorizontalPager(state = pagerState, pageCount = pageCount) { page ->

                Center {
                    Column {

                        Center(fillMaxSize = false) {
                            AppImage(
                                resource = vm.getIllustrationFromPageIndex(page),
                                modifier = Modifier.height(300.dp)
                            )
                        }

                        Text(
                            text = vm.getMessageFromPageIndex(page),
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth()
                        )
                    }
                }

            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pagerState.currentPage < pageCount - 1) {
                TextButton(onClick = navigate, modifier = Modifier
                    .width(80.dp)
                    .height(40.dp)) {
                    Text("Skip")
                }
            } else Box(
                Modifier
                    .width(80.dp)
                    .height(40.dp))

            PageIndicators(pageCount = pageCount, currentPageIndex = pagerState.currentPage)

            if (pagerState.currentPage < pageCount - 1){
                IconButton(onClick =  {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }, modifier = Modifier
                    .width(80.dp)
                    .height(40.dp)) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "", tint = MaterialTheme.colors.primary)
                }

            } else TextButton(onClick = navigate, modifier = Modifier
                .width(80.dp)
                .height(40.dp)) {
                Text(text = "Done")
            }
        }


    }
}