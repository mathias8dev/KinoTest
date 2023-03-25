package com.mathias8dev.kinotest

import android.app.PendingIntent.getActivity
import android.os.Build
import android.os.Bundle
import android.view.*
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mathias8dev.kinotest.domain.viewModel.KVehicleViewModel
import com.mathias8dev.kinotest.ui.screens.KSplashScreen
import com.mathias8dev.kinotest.ui.screens.VehicleDetailsScreen
import com.mathias8dev.kinotest.ui.screens.VehicleListScreen
import com.mathias8dev.kinotest.ui.screens.StartupScreen
import com.mathias8dev.kinotest.ui.theme.KinoTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = KVehicleViewModel()
        super.onCreate(savedInstanceState)

        setContent {
            KinoTestTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "startupScreen") {

                        composable("splashScreen") {
                            KSplashScreen {
                                navController.navigate("startupScreen") {
                                    popUpTo("splashScreen") {inclusive = true}
                                }
                            }
                        }

                        composable("startupScreen") {
                            StartupScreen {
                                navController.navigate("vehicleList") {
                                    popUpTo("startupScreen") {inclusive = true}
                                }
                            }
                        }

                        composable("vehicleList") {
                            VehicleListScreen(
                                vm = vm,
                                onKVehicleClicked = {
                                    navController.navigate("vehicleDetails?vehicleId=$it")
                                }
                            )
                        }

                        composable(
                            route = "vehicleDetails?vehicleId={vehicleId}",
                            arguments = listOf(
                                navArgument("vehicleId") { type = NavType.IntType },
                            )
                        ) { backStackEntry ->
                            VehicleDetailsScreen(
                                vehicleId = backStackEntry.arguments?.getInt("vehicleId")!!,
                                onBackPressed = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }


}

