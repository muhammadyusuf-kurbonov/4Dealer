package uz.muhammadyusuf.kurbonov.fordealer.homescreen

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import uz.muhammadyusuf.kurbonov.fordealer.translations.R

@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.h6
            )
        }
    )
}