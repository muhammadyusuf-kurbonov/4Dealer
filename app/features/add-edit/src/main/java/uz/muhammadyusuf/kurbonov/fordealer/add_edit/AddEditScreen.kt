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
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.di.LocalAddEditComponent
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import uz.muhammadyusuf.kurbonov.shared.ui.*

@Composable
fun AddEditScreen(transaction: Transaction? = null) {
    val navController = LocalNavController.current
    val titleController = LocalTitleController.current
    val appName = stringResource(id = R.string.app_name)
    titleController.changeTitle(
        if (transaction != null) stringResource(id = R.string.edit_transaction)
        else stringResource(R.string.new_transaction)
    )

    DisposableEffect(key1 = Unit){
        onDispose {
            titleController.changeTitle(appName)
        }
    }

    val component = LocalAddEditComponent.current
    val viewModel = component.viewModel

    val addEditState by viewModel.addEditState.collectAsState()

    AddEditContent(
        addEditState = addEditState,
        transaction = transaction,
        save = {
            viewModel.save(it)
        },
        cancel = {
            navController.navigate(NavDestinations.HOME)
        })
}

@Composable
fun AddEditContent(
    addEditState: AddEditState,
    transaction: Transaction? = null,
    save: (Transaction) -> Unit,
    cancel: () -> Unit
) {
    Log.d("ForDealer", addEditState.toString())

    if (addEditState is AddEditState.Saving)
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )

    if (addEditState is AddEditState.Saved) {
        val snackbarController = LocalSnackbarController.current
        val message = stringResource(R.string.saved)
        LaunchedEffect(key1 = Unit) {
            snackbarController.showInfoMessage(message)
            cancel()
        }
    }

    Column(modifier = Modifier.padding(MEDIUM_MARGIN)) {
        var dateTime by remember {
            mutableStateOf(
                transaction?.dateTime
                    ?: System.currentTimeMillis()
            )
        }
        var transactionType by remember {
            mutableStateOf(
                if ((transaction?.amount ?: 1.0) > 0.0) TransactionType.INCOME
                else TransactionType.OUTGOING
            )
        }

        var amount by remember {
            mutableStateOf(0.0)
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
                            id = R.string.revenue
                        ),
                        tint = Color.Green
                    )
                },
                caption = stringResource(R.string.revenue),
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
                amount = try {
                    it.toDouble()
                }catch (e: Exception){
                    amount
                }
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

            Button(
                enabled = amount > 0f,
                onClick = {
                save(
                    Transaction(
                        amount = if (transactionType == TransactionType.INCOME) amount else -amount,
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