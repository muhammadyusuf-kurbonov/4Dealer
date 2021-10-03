package uz.muhammadyusuf.kurbonov.fordealer.homescreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.muhammadyusuf.kurbonov.fordealer.homescreen.components.AppFloatingActionButton
import uz.muhammadyusuf.kurbonov.fordealer.homescreen.di.LocalHomeComponent
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.ui.*
import java.text.NumberFormat

@Composable
fun HomeContent(
    balance: Double,
    navigateTo: (String) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        GeneralReport(
            modifier = Modifier
                .padding(XLARGE_MARGIN),
            balance
        )

        AppFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(XLARGE_MARGIN),
            navigateToAddScreen = {
                navigateTo(NavDestinations.ADD_EDIT)
            })
    }
}

@Composable
fun GeneralReport(
    modifier: Modifier = Modifier,
    balance: Double
) {
    LazyColumn(modifier = modifier) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MEDIUM_MARGIN),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(LARGE_MARGIN)
                ) {
                    Text(text = "Balance", style = MaterialTheme.typography.overline)
                    Row {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = NumberFormat.getInstance().format(balance),
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
            }

        }
    }
}