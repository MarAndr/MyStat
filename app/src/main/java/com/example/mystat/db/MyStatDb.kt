package com.example.mystat.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mystat.programming.ProgrammingStat

@Database(entities = [ProgrammingStat::class], version = DbConstants.DB_VER)
@TypeConverters(DayTypeConverter::class)
abstract class MyStatDb: RoomDatabase() {
    abstract fun programmingDao(): ProgrammingDao
}