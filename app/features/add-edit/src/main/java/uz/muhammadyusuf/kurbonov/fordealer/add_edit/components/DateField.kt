package uz.muhammadyusuf.kurbonov.fordealer.add_edit.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.ui.LARGE_MARGIN
import java.text.DateFormat
import java.text.ParseException

@Composable
fun DateField(
    modifier: Modifier = Modifier,
    dateTimeAsString: String,
    onValueChange: (String) -> Unit
){

    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)

    val notValidDate = try {
        dateFormat.isLenient = false
        dateFormat.parse(dateTimeAsString)
        false
    } catch (e: ParseException) {
        true
    }

    OutlinedTextField(
        modifier = modifier,
        value = dateTimeAsString,
        onValueChange = onValueChange,
        isError = notValidDate,
        label = {
            Text(
                text = stringResource(id = R.string.Date)
            )
        }
    )

    if (notValidDate)
        Text(modifier = modifier.padding(LARGE_MARGIN   , 0.dp),
            text = "Not valid date",
            color = MaterialTheme.colors.error)
}