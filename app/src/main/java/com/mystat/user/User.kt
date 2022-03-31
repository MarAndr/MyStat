package com.mystat.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mystat.db.DbConstants

@Entity(tableName = DbConstants.USER_TABLE_NAME)
data class User(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.UserColumns.UID)
    val uid: String,
    @ColumnInfo(name = DbConstants.UserColumns.NAME)
    val name: String,
    @ColumnInfo(name = DbConstants.UserColumns.EMAIL)
    val email: String
)
