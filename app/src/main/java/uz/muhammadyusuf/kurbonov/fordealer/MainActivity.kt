package uz.muhammadyusuf.kurbonov.fordealer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.AddEditScreen
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.di.LocalAddEditComponent
import uz.muhammadyusuf.kurbonov.fordealer.homescreen.HomeScreen
import uz.muhammadyusuf.kurbonov.fordealer.list.ListScreen
import uz.muhammadyusuf.kurbonov.fordealer.list.di.LocalListComponent
import uz.muhammadyusuf.kurbonov.fordealer.ui.components.AppBar
import uz.muhammadyusuf.kurbonov.fordealer.ui.theme.ForDealerTheme
import uz.muhammadyusuf.kurbonov.shared.ui.*
import uz.muhammadyusuf.kurbonov.shared.ui.controllers.SnackbarController
import uz.muhammadyusuf.kurbonov.shared.ui.controllers.TitleController
import uz.muhammadyusuf.kurbonov.shared.ui.controllers.ToolbarController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var actions: @Composable RowScope.() -> Unit by remember {
                mutableStateOf({})
            }
            val navHostController = rememberNavController()
            val scaffoldState = rememberScaffoldState()

            var title by remember { mutableStateOf(getString(R.string.app_name)) }

            val titleController = TitleController { newTitle ->
                title = newTitle
            }
            val snackbarController = SnackbarController { message ->
                lifecycleScope.launch { scaffoldState.snackbarHostState.showSnackbar(message) }
            }
            val toolbarController = object : ToolbarController {
                override fun setActions(newActions: @Composable RowScope.() -> Unit) {
                    actions = newActions
                }

                override fun clear() {
                    actions = {}
                }

            }

            CompositionLocalProvider(
                LocalNavController provides navHostController,
                LocalTitleController provides titleController,
                LocalSnackbarController provides snackbarController,
                LocalToolbarController provides toolbarController
            ) {
                ForDealerTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Scaffold(
                            scaffoldState = scaffoldState,
                            topBar = { AppBar(title, actions) },
                        ) {
                            NavHost(
                                navController = navHostController,
                                startDestination = NavDestinations.HOME
                            ) {
                                composable(NavDestinations.HOME) {
                                    HomeScreen()
                                }

                                composable(NavDestinations.ADD_EDIT) {
                                    CompositionLocalProvider(
                                        LocalAddEditComponent provides appComponent()
                                            .addEditComponentBuilder()
                                            .build()
                                    ) {
                                        AddEditScreen()
                                    }
                                }

                                composable(NavDestinations.LIST) {
                                    val listComponent = remember {
                                        appComponent()
                                            .listComponentBuilder()
                                            .build()
                                    }
                                    CompositionLocalProvider(
                                        LocalListComponent provides listComponent
                                    ) {
                                        ListScreen()
                                    }
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