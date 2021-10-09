package com.example.mystat.db

import androidx.room.TypeConverter
import com.example.mystat.programming.ProgrammingTypes


class ProgrammingTypeConverter {

    @TypeConverter
    fun convertTypeToString(type: ProgrammingTypes) = type.name

    @TypeConverter
    fun convertStringToType(typeString: String): ProgrammingTypes =
        ProgrammingTypes.valueOf(typeString)
}