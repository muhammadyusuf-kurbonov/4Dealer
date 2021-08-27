package uz.muhammadyusuf.kurbonov.fordealer.add_edit.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.ui.LARGE_MARGIN
import java.text.DateFormat
import java.text.ParseException

@Composable
fun TimeField(
    modifier: Modifier = Modifier,
    dateTime: Long,
    onValueChange: (Long) -> Unit
) {

    val timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT)
    var timeAsString by remember {
        mutableStateOf(timeFormat.format(dateTime))
    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val notValidTime = try {
        timeFormat.isLenient = false
        timeFormat.parse(timeAsString)
        false
    } catch (e: ParseException) {
        true
    }

    OutlinedTextField(
        modifier = modifier,
        value = timeAsString,
        onValueChange = {
            timeAsString = it
            try {
                timeFormat.isLenient = false
                val timeInMillis = timeFormat.parse(timeAsString)
                if (timeInMillis != null)
                    onValueChange(timeInMillis.time)
            } catch (e: ParseException) {
            }
        },
        isError = notValidTime,
        label = {
            Text(
                text = stringResource(id = R.string.Time)
            )
        },
//        trailingIcon = {
//            IconButton(onClick = {
//                scope.launch {
//                    val pickedDate = pickDate(
//                        context,
//                        if (!notValidDate) dateFormat.parse(timeAsString) else Date()
//                    )
//                    onValueChange(dateFormat.format(Date(pickedDate)))
//                }
//            }) {
//                Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
//            }
//        }
    )

    if (notValidTime)
        Text(
            modifier = modifier.padding(LARGE_MARGIN, 0.dp),
            text = "Not valid time",
            color = MaterialTheme.colors.error
        )
}