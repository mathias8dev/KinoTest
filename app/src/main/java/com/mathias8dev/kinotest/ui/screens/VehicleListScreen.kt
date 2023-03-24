package com.mathias8dev.kinotest.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mathias8dev.kinotest.domain.viewModel.KVehicleViewModel


@Composable
fun VehicleListScreen(vm: KVehicleViewModel) {

    LaunchedEffect(Unit, block = {
        vm.getVehicleList()
    })

    Column {

        TopAppBar {
            Row {
                Text("Todos")
            }
        }

        if (vm.errorMessage.isEmpty()) {
            Column(modifier = Modifier.padding(16.dp)) {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(vm.vehiclesList) { vehicle ->
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp, 0.dp, 16.dp, 0.dp)
                                ) {
                                    Text(
                                        vehicle.name,
                                    )
                                }
                            }
                            Divider()
                        }
                    }
                }
            }
        } else {
            Text(vm.errorMessage)
        }
    }
}