package com.mystat.db

import android.content.Context
import androidx.room.Room

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