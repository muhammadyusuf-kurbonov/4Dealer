package uz.muhammadyusuf.kurbonov.fordealer.add_edit

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.components.DateField
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.components.TimeField
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.components.ToggleStatusButton
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import uz.muhammadyusuf.kurbonov.shared.models.TransactionType
import uz.muhammadyusuf.kurbonov.shared.ui.*

@Composable
fun AddEditScreen(transaction: Transaction? = null) {
    val navController = LocalNavController.current
    LocalTitleController.current.changeTitle(
        if (transaction != null) stringResource(id = R.string.edit_transaction)
        else stringResource(R.string.new_transaction)
    )
    AddEditContent(
        transaction = transaction,
        save = {
            Log.d("Add-edit", it.toString())
            navController.navigate(NavDestinations.HOME)
        },
        cancel = {
            navController.navigate(NavDestinations.HOME)
        })
}

@Composable
fun AddEditContent(
    transaction: Transaction? = null,
    save: (Transaction) -> Unit,
    cancel: () -> Unit
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

        var amount by remember {
            mutableStateOf(0f)
        }

        var note by remember {
            mutableStateOf("")
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

        Spacer(modifier = Modifier.height(MEDIUM_MARGIN))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = amount.toString(),
            onValueChange = {
                amount = it.toFloat()
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            leadingIcon = {
                Text(text = "$")
            }
        )

        Spacer(modifier = Modifier.height(MEDIUM_MARGIN))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp),
            value = note,
            onValueChange = {
                note = it
            },
            label = {
                Text(text = stringResource(id = R.string.note))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.note)
                )
            }
        )

        Spacer(modifier = Modifier.height(MEDIUM_MARGIN))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = {
                cancel()
            }) {
                Text(
                    text = stringResource(id = android.R.string.cancel),
                    style = MaterialTheme.typography.button
                )
            }

            Spacer(modifier = Modifier.width(SMALL_MARGIN))

            Button(onClick = {
                save(
                    Transaction(
                        amount = amount,
                        type = transactionType,
                        dateTime = dateTime,
                        note = note
                    )
                )
            }) {
                Text(
                    text = stringResource(id = android.R.string.ok),
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}