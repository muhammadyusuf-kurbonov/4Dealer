package uz.muhammadyusuf.kurbonov.fordealer.add_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.models.Transaction
import uz.muhammadyusuf.kurbonov.shared.ui.MEDIUM_MARGIN

@Composable
fun AddEditScreen(transaction: Transaction? = null) {
    Column(modifier = Modifier.padding(MEDIUM_MARGIN)) {

        Text(
            text = stringResource(
                id =
                if (transaction != null) R.string.edit_transaction
                else R.string.add_transaction
            ),
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.height(MEDIUM_MARGIN))



    }
}