package uz.muhammadyusuf.kurbonov.shared.ui

import androidx.annotation.IntDef
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.toObject
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *  This method assigns timeUnits from [timeSource] to [timeInMillis]
 *  and returns this new value as timeInMillis.
 */
fun assignTime(timeInMillis: Long, timeSource: Long): Long{
    val calendarSource = Calendar.getInstance()
    calendarSource.timeInMillis = timeSource

    val calendarDist = Calendar.getInstance()
    calendarDist.timeInMillis = timeInMillis

    calendarDist[Calendar.HOUR] = calendarSource[Calendar.HOUR]
    calendarDist[Calendar.MINUTE] = calendarSource[Calendar.MINUTE]
    calendarDist[Calendar.SECOND] = calendarSource[Calendar.SECOND]
    return calendarDist.timeInMillis
}


fun assignDate(timeInMillis: Long, timeSource: Long): Long{
    val calendarSource = Calendar.getInstance()
    calendarSource.timeInMillis = timeSource

    val calendarDist = Calendar.getInstance()
    calendarDist.timeInMillis = timeInMillis

    calendarDist[Calendar.DAY_OF_MONTH] = calendarSource[Calendar.DAY_OF_MONTH]
    calendarDist[Calendar.MONTH] = calendarSource[Calendar.MONTH]
    calendarDist[Calendar.YEAR] = calendarSource[Calendar.YEAR]
    return calendarDist.timeInMillis
}

fun Long.toCalendar(): Calendar{
    return Calendar.getInstance().also {
        it.timeInMillis = this
    }
}

@IntDef(
    Calendar.YEAR,
    Calendar.MONTH,
    Calendar.DAY_OF_MONTH,
    Calendar.HOUR,
    Calendar.MINUTE,
    Calendar.SECOND
)
annotation class DateUnits

fun Long.roundDate(@DateUnits to: Int): Long {
    val calendar = this.toCalendar()
    calendar.set(Calendar.MILLISECOND, 0)
    if (to == Calendar.SECOND) return calendar.timeInMillis
    calendar.set(Calendar.SECOND, 0)
    if (to == Calendar.MINUTE) return calendar.timeInMillis
    calendar.set(Calendar.MINUTE, 0)
    if (to == Calendar.HOUR) return calendar.timeInMillis
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    if (to == Calendar.DAY_OF_MONTH) return calendar.timeInMillis
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    if (to == Calendar.MONTH) return calendar.timeInMillis
    calendar.set(Calendar.MONTH, 0)
    if (to == Calendar.YEAR) return calendar.timeInMillis
    return calendar.timeInMillis
}

suspend inline fun <reified T> DocumentReference.readDocument() = suspendCoroutine<T?>{ cont ->
    get().addOnSuccessListener {
        cont.resume(it.toObject(T::class.java))
    }.addOnFailureListener {
        cont.resumeWithException(it)
    }
}