package uz.muhammadyusuf.kurbonov.fordealer.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import uz.muhammadyusuf.kurbonov.fordealer.list.di.LocalListComponent
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import uz.muhammadyusuf.kurbonov.shared.ui.MEDIUM_MARGIN
import java.text.DateFormat

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
    LazyColumn {
        items(items) {
            ListItem(item = it)
        }
    }
}

@Composable
fun ListItem(item: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MEDIUM_MARGIN)
    ) {
        val dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM)
        Text(
            modifier = Modifier.weight(1f),
            text = dateFormat.format(item.dateTime),
            style = MaterialTheme.typography.body1
        )
        Text(text = item.amount.toString(), style = MaterialTheme.typography.body1, fontWeight = FontWeight.Bold)
    }
    Divider()
}