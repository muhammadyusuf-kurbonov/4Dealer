package uz.muhammadyusuf.kurbonov.fordealer.homescreen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import uz.muhammadyusuf.kurbonov.shared.ui.LocalNavController
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
    DisposableEffect(key1 = Unit) {
        onDispose {
            toolbarController.clear()
        }
    }
    HomeContent(navigateTo = {
        navHostController.navigate(it)
    })
}

@Preview()
@Composable
fun HomeScreenPreview() {
    HomeContent()
}