package uz.muhammadyusuf.kurbonov.fordealer.add_edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.components.DateField
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.components.TimeField
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.components.ToggleStatusButton
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import uz.muhammadyusuf.kurbonov.shared.models.TransactionType
import uz.muhammadyusuf.kurbonov.shared.ui.LocalTitleController
import uz.muhammadyusuf.kurbonov.shared.ui.MEDIUM_MARGIN
import uz.muhammadyusuf.kurbonov.shared.ui.SMALL_MARGIN

@Composable
fun AddEditScreen(transaction: Transaction? = null) {
    LocalTitleController.current.changeTitle(
        if (transaction != null) stringResource(id = R.string.edit_transaction)
        else stringResource(R.string.new_transaction)
    )
    AddEditContent(
        transaction = transaction,
        save = {

        })
}

@Composable
fun AddEditContent(
    transaction: Transaction? = null,
    save: (Transaction) -> Unit
) {
    Column(modifier = Modifier.padding(MEDIUM_MARGIN)) {

        var dateTime by remember {
            mutableStateOf(
                transaction?.dateTime
                    ?: System.currentTimeMillis()
            )
        }
        var transactionType by remember {
            mutableStateOf(
                transaction?.type ?: TransactionType.INCOME
            )
        }

        LaunchedEffect(key1 = Unit) {
            dateTime = System.currentTimeMillis()
        }

        Spacer(modifier = Modifier.height(MEDIUM_MARGIN))

        Row(modifier = Modifier.fillMaxWidth()) {
            DateField(
                modifier = Modifier.weight(3f),
                dateTime = dateTime,
                onValueChange = {
                    dateTime = it
                })

            Spacer(modifier = Modifier.width(SMALL_MARGIN))

            TimeField(
                modifier = Modifier.weight(2f),
                dateTime = dateTime,
                onValueChange = {
                    dateTime = it
                })
        }

        Spacer(modifier = Modifier.height(MEDIUM_MARGIN))

        Row {
            ToggleStatusButton(
                modifier = Modifier.weight(1f),
                icon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = stringResource(
                            id = R.string.income
                        ),
                        tint = Color.Green
                    )
                },
                caption = stringResource(R.string.income),
                activated = transactionType == TransactionType.INCOME,
                onActivate = {
                    transactionType = TransactionType.INCOME
                })

            ToggleStatusButton(
                modifier = Modifier.weight(1f),
                icon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = stringResource(
                            id = R.string.expense
                        ),
                        tint = Color.Red
                    )
                },
                caption = stringResource(R.string.expense),
                activated = transactionType == TransactionType.OUTGOING,
                onActivate = {
                    transactionType = TransactionType.OUTGOING
                })
        }
    }
}