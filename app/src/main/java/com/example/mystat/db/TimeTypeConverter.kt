package com.example.mystat.db

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class TimeTypeConverter {

    @TypeConverter
    fun convertLocalDateTimeToString(time: LocalDateTime) = time.toString()

    @TypeConverter
    fun convertStringToLocalDate(timeString: String): LocalDateTime {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        return LocalDateTime.parse(timeString, formatter)
    }


}