package com.mystat.db

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DayTypeConverter {

    @TypeConverter
    fun convertLocalDateToString(day: LocalDate) = day.toString()

    @TypeConverter
    fun convertStringToDate(dayString: String): LocalDate {
        val formatter = DateTimeFormatter.ISO_DATE
        return LocalDate.parse(dayString, formatter)
    }


}