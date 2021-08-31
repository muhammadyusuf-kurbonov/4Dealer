package uz.muhammadyusuf.kurbonov.fordealer.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import uz.muhammadyusuf.kurbonov.fordealer.list.di.LocalListComponent
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import uz.muhammadyusuf.kurbonov.shared.ui.SMALL_MARGIN
import uz.muhammadyusuf.kurbonov.shared.ui.XLARGE_MARGIN
import uz.muhammadyusuf.kurbonov.shared.ui.roundDate
import java.text.DateFormat
import java.util.*

@Composable
fun ListScreen() {
    val component = LocalListComponent.current
    val viewModel = component.listViewModel

    val transactions by viewModel.allTransactions.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.startListening()
    }
    ListContent(transactions)
}

@Composable
fun ListContent(items: List<Transaction>) {
    if (items.isNotEmpty()) {
        val grouped = items.groupBy {
            it.dateTime.roundDate(Calendar.DAY_OF_MONTH)
        }
        LazyColumn {
            grouped.keys.forEach { key ->
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(XLARGE_MARGIN, XLARGE_MARGIN, XLARGE_MARGIN),
                        text = DateFormat.getDateInstance().format(key),
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                }
                items(grouped[key] ?: emptyList()) {
                    ListItem(item = it)
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.no_data),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun ListItem(item: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(XLARGE_MARGIN)
    ) {
        val dateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM)
        Column(modifier = Modifier.weight(1f)) {
            if (item.note.isNotEmpty()) {
                Text(
                    text = item.note,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(SMALL_MARGIN))
                Text(
                    text = dateFormat.format(item.dateTime),
                    style = MaterialTheme.typography.caption
                )
            }else{
                Text(
                    text = dateFormat.format(item.dateTime),
                    style = MaterialTheme.typography.body1
                )
            }
        }
        Text(
            text = item.amount.toString(),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            color = if (item.amount > 0) Color(0xFF006F3B) else Color.Red
        )
    }
    Divider()
}