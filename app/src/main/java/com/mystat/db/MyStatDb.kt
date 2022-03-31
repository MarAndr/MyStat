package com.mystat.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mystat.programming.ProgrammingStat
import com.mystat.user.User

@Database(entities = [ProgrammingStat::class, User::class], version = DbConstants.DB_VER)
@TypeConverters(DayTypeConverter::class)
abstract class MyStatDb: RoomDatabase() {
    abstract fun programmingDao(): ProgrammingDao
    abstract fun userDao(): UserDao
}