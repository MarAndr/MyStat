package com.example.mystat.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object Database {
    lateinit var instance: MyStatDb

    fun init(context: Context){
        instance = Room.databaseBuilder(
            context,
            MyStatDb::class.java,
            DbConstants.DB_NAME)
            .build()
    }
}