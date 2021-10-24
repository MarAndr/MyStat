package com.mystat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mystat.programming.ProgrammingStat
import com.mystat.programming.ProgrammingTypes
import java.time.LocalDate

@Dao
interface ProgrammingDao {

    @Insert
    suspend fun addProgrammingStat(stat: List<ProgrammingStat>)

    @Query("SELECT * FROM ${DbConstants.PROGR_TABLE_NAME}")
    suspend fun getProgrammingStat(): List<ProgrammingStat>

    @Query("SELECT * FROM ${DbConstants.PROGR_TABLE_NAME} WHERE ${DbConstants.ProgrammingColumns.ID} = :id")
    suspend fun getProgrammingStatById(id: Long): ProgrammingStat

    @Query("update ${DbConstants.PROGR_TABLE_NAME} set ${DbConstants.ProgrammingColumns.DURATION} = :duration where ${DbConstants.ProgrammingColumns.ID} = :statId")
    suspend fun updateDurationStat(statId: Long, duration: Int)

    @Query("update ${DbConstants.PROGR_TABLE_NAME} set ${DbConstants.ProgrammingColumns.TYPE} = :type where ${DbConstants.ProgrammingColumns.ID} = :statId")
    suspend fun updateTypeStat(statId: Long, type: ProgrammingTypes)

    @Query("SELECT SUM(${DbConstants.ProgrammingColumns.DURATION}) FROM ${DbConstants.PROGR_TABLE_NAME}")
    fun sumAllCategories(): Int

    @Query("delete from ${DbConstants.PROGR_TABLE_NAME} where ${DbConstants.ProgrammingColumns.ID} = :id")
    suspend fun deleteStatElement(id: Long)

//    @Query("SELECT SUM(${DbConstants.ProgrammingColumns.DURATION}) FROM ${DbConstants.PROGR_TABLE_NAME} WHERE ${} = :year")
//    fun sumAllCategoriesThisYear(year: LocalDate = LocalDate.now()): Int

    @Query("SELECT SUM(${DbConstants.ProgrammingColumns.DURATION}) FROM ${DbConstants.PROGR_TABLE_NAME} WHERE ${DbConstants.ProgrammingColumns.DAY} = :day")
    fun sumAllCategoriesToday(day: LocalDate = LocalDate.now()): Int

    @Query("SELECT SUM(${DbConstants.ProgrammingColumns.DURATION}) FROM ${DbConstants.PROGR_TABLE_NAME} WHERE ${DbConstants.ProgrammingColumns.TYPE} = :type ")
    suspend fun sumAllDurationsByType(type: ProgrammingTypes): Int

    @Query("SELECT SUM(${DbConstants.ProgrammingColumns.DURATION}) FROM ${DbConstants.PROGR_TABLE_NAME} WHERE ${DbConstants.ProgrammingColumns.TYPE} = :type AND ${DbConstants.ProgrammingColumns.DAY} = :day")
    suspend fun sumAllDurationsByTypeToday(type: ProgrammingTypes, day: LocalDate = LocalDate.now()): Int

    @Query("SELECT SUM(${DbConstants.ProgrammingColumns.DURATION}) FROM ${DbConstants.PROGR_TABLE_NAME} WHERE ${DbConstants.ProgrammingColumns.TYPE} = :type AND ${DbConstants.ProgrammingColumns.DAY} = :day")
    suspend fun sumAllDurationsByTypeThisMonth(type: ProgrammingTypes, day: LocalDate = LocalDate.now()): Int
}