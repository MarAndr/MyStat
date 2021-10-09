package com.example.mystat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mystat.programming.ProgrammingStat
import com.example.mystat.programming.ProgrammingTypes
import java.time.LocalDate

@Dao
interface ProgrammingDao {

    @Insert
    suspend fun addProgrammingStat(stat: List<ProgrammingStat>)

    @Query("SELECT * FROM ${DbConstants.PROGR_TABLE_NAME}")
    suspend fun getProgrammingStat(): List<ProgrammingStat>

    @Query("SELECT SUM(${DbConstants.ProgrammingColumns.DURATION}) FROM ${DbConstants.PROGR_TABLE_NAME}")
    fun sumAllCategories(): Int

    @Query("SELECT SUM(${DbConstants.ProgrammingColumns.DURATION}) FROM ${DbConstants.PROGR_TABLE_NAME} WHERE ${DbConstants.ProgrammingColumns.DAY} = :day")
    fun sumAllCategoriesToday(day: LocalDate = LocalDate.now()): Int

    @Query("SELECT SUM(${DbConstants.ProgrammingColumns.DURATION}) FROM ${DbConstants.PROGR_TABLE_NAME} WHERE ${DbConstants.ProgrammingColumns.TYPE} = :type ")
    suspend fun sumAllDurationsByType(type: ProgrammingTypes): Int

    @Query("SELECT SUM(${DbConstants.ProgrammingColumns.DURATION}) FROM ${DbConstants.PROGR_TABLE_NAME} WHERE ${DbConstants.ProgrammingColumns.TYPE} = :type AND ${DbConstants.ProgrammingColumns.DAY} = :day")
    suspend fun sumAllDurationsByTypeToday(type: ProgrammingTypes, day: LocalDate = LocalDate.now()): Int
}