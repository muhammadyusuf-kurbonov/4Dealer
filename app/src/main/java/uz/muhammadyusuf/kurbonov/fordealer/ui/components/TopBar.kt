package uz.muhammadyusuf.kurbonov.fordealer.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import uz.muhammadyusuf.kurbonov.fordealer.translations.R

@Composable
fun AppBar(title: String? = null) {
    TopAppBar(
        title = {
            Text(
                text = title ?: stringResource(R.string.app_name),
                style = MaterialTheme.typography.h6
            )
        }
    )
}