package com.mathias8dev.kinotest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mathias8dev.kinotest.R
import com.mathias8dev.kinotest.domain.data.KVUiState
import com.mathias8dev.kinotest.domain.data.KVehicle
import com.mathias8dev.kinotest.domain.data.Strings
import com.mathias8dev.kinotest.domain.viewModel.KVehicleViewModel
import com.mathias8dev.kinotest.ui.components.Center
import com.mathias8dev.kinotest.ui.components.KLottieAnimation


@Composable
fun VehicleListScreen(
    vm: KVehicleViewModel,
    onKVehicleClicked: (Int) -> Unit = {}
) {

    val state by vm.uiState.collectAsState()

    when (state) {
        is KVUiState.Empty -> {
            LaunchedEffect(Unit, block = {
                vm.getVehicleList()
            })

        }

        is KVUiState.Loading -> Center {
            KLottieAnimation(lottieFile = R.raw.loading)
        }

        is KVUiState.Error -> ErrorView(message = (state as KVUiState.Error).message) {
            vm.getVehicleList()
        }

        is KVUiState.Loaded -> DataLoadedView(
            data = (state as KVUiState.Loaded).data,
            onKVehicleClicked = onKVehicleClicked
        )
    }


}

@Composable
fun ErrorView(message: String, onReload: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        KLottieAnimation(lottieFile = R.raw.error)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = message, style = TextStyle(fontSize = 20.sp))
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onReload) {
            Text(Strings.reload)
        }
    }
}

@Composable
fun DataLoadedView(data: List<KVehicle>, onKVehicleClicked: (Int) -> Unit = {}) {
    Column {

        TopAppBar {
            Row {
                Text(
                    stringResource(id = R.string.app_name),
                    modifier = Modifier.padding(start = 20.dp),
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(data) { vehicle ->
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onKVehicleClicked(vehicle.id) }
                                .padding(top = 16.dp, bottom = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(vehicle.icon.url.left),
                                contentDescription = "",
                                modifier = Modifier.size(32.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp, 0.dp, 16.dp, 0.dp)
                            ) {
                                Text(
                                    vehicle.name,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        Divider()
                    }
                }
            }
        }

    }
}