package uz.muhammadyusuf.kurbonov.fordealer.add_edit.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
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
    dateTime: Long,
    onValueChange: (Long) -> Unit
) {

    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
    var dateTimeAsString by remember {
        mutableStateOf(dateFormat.format(dateTime))
    }
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
        onValueChange = {
            dateTimeAsString = it
            try {
                dateFormat.isLenient = false
                val date = dateFormat.parse(dateTimeAsString)
                if (date != null) onValueChange(date.time)
            } catch (e: ParseException) {
                true
            }
        },
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
                    onValueChange(pickedDate)
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