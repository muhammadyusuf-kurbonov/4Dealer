package uz.muhammadyusuf.kurbonov.shared.ui

import java.util.*

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