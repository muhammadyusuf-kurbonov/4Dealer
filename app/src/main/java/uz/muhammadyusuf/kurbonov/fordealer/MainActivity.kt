package uz.muhammadyusuf.kurbonov.fordealer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.AddEditScreen
import uz.muhammadyusuf.kurbonov.fordealer.homescreen.HomeScreen
import uz.muhammadyusuf.kurbonov.fordealer.ui.components.AppBar
import uz.muhammadyusuf.kurbonov.fordealer.ui.components.AppFloatingActionButton
import uz.muhammadyusuf.kurbonov.fordealer.ui.theme.ForDealerTheme
import uz.muhammadyusuf.kurbonov.shared.ui.LocalNavController
import uz.muhammadyusuf.kurbonov.shared.ui.NavDestinations

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            CompositionLocalProvider(
                LocalNavController provides navHostController
            ) {
                ForDealerTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val scaffoldState = rememberScaffoldState()
                        Scaffold(
                            scaffoldState = scaffoldState,
                            topBar = { AppBar() },
                            floatingActionButton = {
                                AppFloatingActionButton(navigateToAddScreen = {
                                    navHostController.navigate(
                                        NavDestinations.ADD_EDIT
                                    )
                                })
                            }
                        ) {
                            NavHost(
                                navController = navHostController,
                                startDestination = NavDestinations.HOME
                            ) {
                                composable(NavDestinations.HOME) {
                                    HomeScreen()
                                }

                                composable(NavDestinations.ADD_EDIT) {
                                    AddEditScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    ForDealerTheme {
        HomeScreen()
    }
}