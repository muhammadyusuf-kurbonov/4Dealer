package uz.muhammadyusuf.kurbonov.fordealer.add_edit.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uz.muhammadyusuf.kurbonov.fordealer.translations.R
import uz.muhammadyusuf.kurbonov.shared.ui.LARGE_MARGIN
import uz.muhammadyusuf.kurbonov.shared.ui.assignTime
import uz.muhammadyusuf.kurbonov.shared.ui.pickTime
import java.text.DateFormat
import java.text.ParseException
import java.util.*

@Composable
fun TimeField(
    modifier: Modifier = Modifier,
    dateTime: Long,
    onValueChange: (Long) -> Unit
) {
    val timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT)
    var timeAsString by remember(dateTime) {
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
                    onValueChange(assignTime(dateTime, timeInMillis.time))
            } catch (e: ParseException) {
            }
        },
        isError = notValidTime,
        label = {
            Text(
                text = stringResource(id = R.string.Time)
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                scope.launch {
                    val pickedTime = pickTime(
                        context,
                        if (!notValidTime) Date(dateTime) else Date()
                    )
                    onValueChange(pickedTime)
                }
            }) {
                Icon(
                    painter = painterResource(
                        id =
                        uz.muhammadyusuf.kurbonov.fordealer.add_edit.
                                R.drawable.ic_baseline_access_time_24
                    ),
                    contentDescription = stringResource(R.string.select_time)
                )
            }
        },
        singleLine = true,
    )

    if (notValidTime)
        Text(
            modifier = modifier.padding(LARGE_MARGIN, 0.dp),
            text = "Not valid time",
            color = MaterialTheme.colors.error
        )
}