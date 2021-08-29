package uz.muhammadyusuf.kurbonov.fordealer.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.ui.LocalNavController
import uz.muhammadyusuf.kurbonov.shared.ui.LocalTitleController

@Composable
fun HomeScreen() {
    LocalTitleController.current.changeTitle(stringResource(id = R.string.app_name))
    val navHostController = LocalNavController.current
    HomeContent(navigateTo = {
        navHostController.navigate(it)
    })
}

@Preview()
@Composable
fun HomeScreenPreview() {
    HomeContent()
}