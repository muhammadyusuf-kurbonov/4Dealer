package uz.muhammadyusuf.kurbonov.fordealer.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uz.muhammadyusuf.kurbonov.fordealer.homescreen.components.AppFloatingActionButton
import uz.muhammadyusuf.kurbonov.shared.ui.NavDestinations
import uz.muhammadyusuf.kurbonov.shared.ui.XLARGE_MARGIN

@Composable
fun HomeContent(
    navigateTo: (String) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Welcome", modifier = Modifier.align(
                Alignment.Center
            )
        )

        Button(
            onClick = { navigateTo(NavDestinations.LIST) },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "View list")
        }

        AppFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(XLARGE_MARGIN),
            navigateToAddScreen = {
                navigateTo(NavDestinations.ADD_EDIT)
            })
    }
}

