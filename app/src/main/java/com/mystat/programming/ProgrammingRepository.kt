package com.mystat.programming

import com.mystat.db.Database

class ProgrammingRepository {

    private val programmingDao = Database.instance.programmingDao()

    suspend fun getProgrammingStat(): List<ProgrammingStat>{
        return programmingDao.getProgrammingStat()
    }

    suspend fun getProgrammingStatById(id: Long): ProgrammingStat{
        return programmingDao.getProgrammingStatById(id)
    }

    suspend fun updateDurationStat(id: Long, duration: Int) {
        return programmingDao.updateDurationStat(id, duration)
    }

    suspend fun updateTypeStat(id: Long, type: ProgrammingTypes) {
        return programmingDao.updateTypeStat(id, type)
    }

    suspend fun deleteStatElement(id: Long){
        return programmingDao.deleteStatElement(id)
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