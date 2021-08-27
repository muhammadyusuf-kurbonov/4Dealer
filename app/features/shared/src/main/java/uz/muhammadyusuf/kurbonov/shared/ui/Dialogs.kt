package uz.muhammadyusuf.kurbonov.shared.ui

import android.app.DatePickerDialog
import android.content.Context
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun pickDate(context: Context, initialDate: Date = Date()) = suspendCoroutine<Long>{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = initialDate.time
    val listener: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener{ _, year, month, day ->
        calendar[Calendar.YEAR] = year
        calendar[Calendar.MONTH]=month
        calendar[Calendar.DAY_OF_MONTH]=day
        it.resume(calendar.timeInMillis)
    }
    DatePickerDialog(
        context,
        listener,
        calendar[Calendar.YEAR],
        calendar[Calendar.MONTH],
        calendar[Calendar.DAY_OF_MONTH]
    ).show()
}