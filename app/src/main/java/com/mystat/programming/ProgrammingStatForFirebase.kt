package com.mystat.programming

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mystat.db.DayTypeConverter
import com.mystat.db.DbConstants
import com.mystat.db.ProgrammingTypeConverter
import com.mystat.db.TimeTypeConverter
import java.time.LocalDate
import java.time.LocalDateTime

data class ProgrammingStatForFirebase(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbConstants.ProgrammingColumns.ID)
    val id: Long = 0,
    @ColumnInfo(name = DbConstants.ProgrammingColumns.TIME)
    val time: String = LocalDateTime.now().toString(),
    @ColumnInfo(name = DbConstants.ProgrammingColumns.DAY)
    val day: String = LocalDate.now().toString(),
    @ColumnInfo(name = DbConstants.ProgrammingColumns.DURATION)
    val durationInMin: Int,
    @ColumnInfo(name = DbConstants.ProgrammingColumns.TYPE)
    val type: ProgrammingTypes
)
