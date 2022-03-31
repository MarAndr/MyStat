package com.mystat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mystat.programming.ProgrammingStat
import com.mystat.user.User

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: List<User>)

    @Query("SELECT * FROM ${DbConstants.USER_TABLE_NAME}")
    suspend fun getUsers(): List<User>
}