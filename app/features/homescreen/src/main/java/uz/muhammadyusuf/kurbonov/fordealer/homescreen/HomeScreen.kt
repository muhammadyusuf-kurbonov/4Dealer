package uz.muhammadyusuf.kurbonov.fordealer.homescreen

import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uz.muhammadyusuf.kurbonov.fordealer.homescreen.di.LocalHomeComponent
import uz.muhammadyusuf.kurbonov.shared.ui.LocalNavController
import uz.muhammadyusuf.kurbonov.shared.ui.LocalTitleController
import uz.muhammadyusuf.kurbonov.shared.ui.LocalToolbarController
import uz.muhammadyusuf.kurbonov.shared.ui.NavDestinations

@Composable
fun HomeScreen() {
    val navHostController = LocalNavController.current
    val toolbarController = LocalToolbarController.current
    toolbarController.setActions {
        IconButton(onClick = { navHostController.navigate(NavDestinations.LIST) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_history_24),
                contentDescription = "History"
            )
        }
    }
    val titleController = LocalTitleController.current
    val appName = stringResource(id = uz.muhammadyusuf.kurbonov.fordealer.translations.R.string.app_name)

    DisposableEffect(key1 = Unit) {
        onDispose {
            titleController.changeTitle(appName)
            toolbarController.clear()
        }
    }

    val component = LocalHomeComponent.current
    val viewModel = remember {
        component.homeViewModel
    }
    val balance by viewModel.balance.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.startListening()
    }

    Log.d("ForDealer", "Balance is $balance")
    HomeContent(
        balance,
        navigateTo = {
            navHostController.navigate(it)
        })
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeContent(1254.4)
}