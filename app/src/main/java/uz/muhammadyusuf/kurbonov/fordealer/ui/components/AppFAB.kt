package uz.muhammadyusuf.kurbonov.fordealer.ui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun AppFloatingActionButton(navigateToAddScreen: () -> Unit = {}) {
    FloatingActionButton(
        onClick = {
            navigateToAddScreen()
        }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "")
    }
}