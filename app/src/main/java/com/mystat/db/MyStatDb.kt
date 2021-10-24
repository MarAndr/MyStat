package com.mystat.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mystat.programming.ProgrammingStat

@Database(entities = [ProgrammingStat::class], version = DbConstants.DB_VER)
@TypeConverters(DayTypeConverter::class)
abstract class MyStatDb: RoomDatabase() {
    abstract fun programmingDao(): ProgrammingDao
}