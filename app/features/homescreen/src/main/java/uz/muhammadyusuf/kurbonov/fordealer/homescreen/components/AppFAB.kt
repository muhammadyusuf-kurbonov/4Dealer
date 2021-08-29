package uz.muhammadyusuf.kurbonov.fordealer.homescreen.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppFloatingActionButton(
    modifier: Modifier = Modifier,
    navigateToAddScreen: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = {
            navigateToAddScreen()
        }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "")
    }
}