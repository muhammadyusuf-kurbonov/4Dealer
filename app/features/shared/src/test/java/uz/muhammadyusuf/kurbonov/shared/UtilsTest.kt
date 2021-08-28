package uz.muhammadyusuf.kurbonov.shared

import org.junit.Assert
import org.junit.Test
import uz.muhammadyusuf.kurbonov.shared.ui.assignDate
import uz.muhammadyusuf.kurbonov.shared.ui.assignTime
import uz.muhammadyusuf.kurbonov.shared.ui.toCalendar
import java.util.Calendar.*


class UtilsTest {
    @Test
    fun `Long2Calendar extension test`() {
        val dateTime1 = System.currentTimeMillis()
        val dateTime1Calendar = System.currentTimeMillis().toCalendar()
        assignTime(dateTime1, dateTime1Calendar.timeInMillis)
    }

    @Test
    fun `assign time - changing time fields`() {
        val dateTime1 = System.currentTimeMillis()
        val dateTime2Calendar = getInstance()

        dateTime2Calendar[HOUR] = 11
        dateTime2Calendar[MINUTE] = 52
        dateTime2Calendar[SECOND] = 2
        // end prepare
        // test
        val dateTime3 =
            assignTime(dateTime1, dateTime2Calendar.timeInMillis)
        val dateTime1Calendar = dateTime3.toCalendar()
        // validate
        Assert.assertEquals(11, dateTime1Calendar[HOUR])
        Assert.assertEquals(52, dateTime1Calendar[MINUTE])
        Assert.assertEquals(2, dateTime1Calendar[SECOND])
    }


    @Test
    fun `assign time - not changing date fields`() {
        val dateTime1Calendar = System.currentTimeMillis().toCalendar()
        val dateTime2Calendar = getInstance()

        dateTime1Calendar[YEAR]=2021
        dateTime1Calendar[MONTH]=7
        dateTime1Calendar[DAY_OF_MONTH]=20

        dateTime2Calendar[HOUR] = 11
        dateTime2Calendar[MINUTE] = 52
        dateTime2Calendar[SECOND] = 2
        // end prepare
        // test
        val dateTime3 =
            assignTime(dateTime1Calendar.timeInMillis,
                dateTime2Calendar.timeInMillis)
        val dateTime3Calendar = dateTime3.toCalendar()
        // validate
        Assert.assertEquals(20, dateTime3Calendar[DAY_OF_MONTH])
        Assert.assertEquals(7, dateTime1Calendar[MONTH])
        Assert.assertEquals(2021, dateTime1Calendar[YEAR])
    }


    @Test
    fun `assign date - changing date fields`() {
        val dateTime1 = System.currentTimeMillis()
        val dateTime2Calendar = getInstance()

        dateTime2Calendar[YEAR]=2021
        dateTime2Calendar[MONTH]=7
        dateTime2Calendar[DAY_OF_MONTH]=20
        // end prepare
        // test
        val dateTime3 =
            assignDate(dateTime1, dateTime2Calendar.timeInMillis)
        val dateTime3Calendar = dateTime3.toCalendar()
        // validate
        Assert.assertEquals(2021, dateTime3Calendar[YEAR])
        Assert.assertEquals(7, dateTime3Calendar[MONTH])
        Assert.assertEquals(20, dateTime3Calendar[DAY_OF_MONTH])
    }

    @Test
    fun `assign date - not changing time fields`() {
        val dateTime1Calendar = System.currentTimeMillis().toCalendar()
        val dateTime2Calendar = getInstance()

        dateTime2Calendar[YEAR]=2021
        dateTime2Calendar[MONTH]=7
        dateTime2Calendar[DAY_OF_MONTH]=20

        dateTime1Calendar[HOUR] = 11
        dateTime1Calendar[MINUTE] = 52
        dateTime1Calendar[SECOND] = 2
        // end prepare
        // test
        val dateTime3 =
            assignDate(dateTime1Calendar.timeInMillis,
                dateTime2Calendar.timeInMillis)
        val dateTime3Calendar = dateTime3.toCalendar()
        // validate
        Assert.assertEquals(11, dateTime3Calendar[HOUR])
        Assert.assertEquals(52, dateTime1Calendar[MINUTE])
        Assert.assertEquals(2, dateTime1Calendar[SECOND])
    }
}