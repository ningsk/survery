package com.zydcc.zrdc.data

import androidx.room.TypeConverter
import java.util.*

/**
 * =======================================
 * Type converters to allow Room to reference complex data types
 * Create by ningsikai 2020/5/18:3:14 PM
 * ========================================
 */
class Converters {
    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long =  calendar.timeInMillis

    @TypeConverter fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}