package com.example.mystat.programming

import com.example.mystat.db.Database
import timber.log.Timber

class ProgrammingRepository {

    private val programmingDao = Database.instance.programmingDao()

    suspend fun getProgrammingStat(): List<ProgrammingStat>{
        return programmingDao.getProgrammingStat()
    }

    suspend fun addProgrammingStat(programmingStat: List<ProgrammingStat>){
        programmingDao.addProgrammingStat(programmingStat)
    }

    fun getAllSum(): Int{
        return programmingDao.sumAllCategories()
    }

    fun getAllSumToday(): Int{
       return programmingDao.sumAllCategoriesToday()
    }

    suspend fun getAllSumByType(types: ProgrammingTypes): Int{
        return programmingDao.sumAllDurationsByType(types)
    }

    suspend fun getAllSumByTypeToday(types: ProgrammingTypes): Int{
        return programmingDao.sumAllDurationsByTypeToday(types)
    }
}