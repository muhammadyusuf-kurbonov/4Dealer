package uz.muhammadyusuf.kurbonov.fordealer.add_edit

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.components.DateField
import uz.muhammadyusuf.kurbonov.fordealer.add_edit.components.TimeField
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import uz.muhammadyusuf.kurbonov.shared.ui.LocalTitleController
import uz.muhammadyusuf.kurbonov.shared.ui.MEDIUM_MARGIN
import java.text.DateFormat
import java.util.*

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

        val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)

        var dateTime by remember {
            mutableStateOf(
                        transaction?.dateTime
                            ?: System.currentTimeMillis()
            )
        }

        Spacer(modifier = Modifier.height(MEDIUM_MARGIN))

        DateField(
            modifier = Modifier.fillMaxWidth(),
            dateTime = dateTime,
            onValueChange = {
                dateTime = it
            })


        TimeField(
            modifier = Modifier.fillMaxWidth(),
            dateTime = dateTime,
            onValueChange = {
                dateTime = it
            })
    }
}