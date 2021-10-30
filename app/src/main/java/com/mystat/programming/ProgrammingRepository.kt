package com.mystat.programming

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mystat.db.Database
import com.mystat.db.DbConstants
import timber.log.Timber

class ProgrammingRepository {

    private val programmingDao = Database.instance.programmingDao()
    lateinit var databaseReference: DatabaseReference

    init {
        val db: FirebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = db.getReference(DbConstants.PROGR_TABLE_NAME)

    }

    fun add (programmingStat: ProgrammingStatForFirebase): Task<Void>{
        return databaseReference.push().setValue(programmingStat).addOnSuccessListener { success ->
            Timber.d("success")
        }.addOnFailureListener {
            Timber.d("failure message = ${it.message}")
        }
    }



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