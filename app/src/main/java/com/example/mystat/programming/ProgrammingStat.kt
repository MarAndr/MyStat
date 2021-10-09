package com.example.mystat.programming

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mystat.db.DayTypeConverter
import com.example.mystat.db.DbConstants
import com.example.mystat.db.ProgrammingTypeConverter
import com.example.mystat.db.TimeTypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = DbConstants.PROGR_TABLE_NAME)
@TypeConverters(value = [ProgrammingTypeConverter::class, TimeTypeConverter::class, DayTypeConverter::class])
data class ProgrammingStat(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbConstants.ProgrammingColumns.ID)
    val id: Int,
    @ColumnInfo(name = DbConstants.ProgrammingColumns.TIME)
    val time: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = DbConstants.ProgrammingColumns.DAY)
    val day: LocalDate = LocalDate.now(),
    @ColumnInfo(name = DbConstants.ProgrammingColumns.DURATION)
    val durationInMin: Int,
    @ColumnInfo(name = DbConstants.ProgrammingColumns.TYPE)
    val type: ProgrammingTypes
)
