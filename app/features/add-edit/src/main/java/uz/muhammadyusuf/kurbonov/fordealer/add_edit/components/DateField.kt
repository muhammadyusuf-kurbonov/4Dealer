package uz.muhammadyusuf.kurbonov.fordealer.add_edit.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.ui.LARGE_MARGIN
import uz.muhammadyusuf.kurbonov.shared.ui.pickDate
import java.text.DateFormat
import java.text.ParseException
import java.util.*

@Composable
fun DateField(
    modifier: Modifier = Modifier,
    dateTimeAsString: String,
    onValueChange: (String) -> Unit
) {

    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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
        },
        trailingIcon = {
            IconButton(onClick = {
                scope.launch {
                    val pickedDate = pickDate(
                        context,
                        if (!notValidDate) dateFormat.parse(dateTimeAsString) else Date()
                    )
                    onValueChange(dateFormat.format(Date(pickedDate)))
                }
            }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
            }
        }
    )

    if (notValidDate)
        Text(
            modifier = modifier.padding(LARGE_MARGIN, 0.dp),
            text = "Not valid date",
            color = MaterialTheme.colors.error
        )
}