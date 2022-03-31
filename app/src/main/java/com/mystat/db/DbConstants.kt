package com.mystat.db

object DbConstants {

    const val DB_NAME = "my_stat"
    const val DB_VER = 1

    const val USER_TABLE_NAME = "user_table"
    const val PROGR_TABLE_NAME = "programming_table"
    const val ENG_TABLE_NAME = "eng_table"
    const val CHESS_TABLE_NAME = "chess_table"
    const val READING_TABLE_NAME = "reading_table"
    const val MEDITATION_TABLE_NAME = "meditation_table"
    const val BRAIN_TABLE_NAME = "brain_table"
    const val ART_TABLE_NAME = "art_table"
    const val FOOTBALL_TABLE_NAME = "programming_table"
    const val VIDEO_TABLE_NAME = "video_table"

    object ProgrammingColumns{
        const val ID = "id"
        const val DURATION = "duration"
        const val TIME = "time"
        const val DAY = "day"
        const val TYPE = "type"
        const val USER_ID = "uid"
    }

    object UserColumns{
        const val UID = "uid"
        const val NAME = "name"
        const val EMAIL = "email"
    }
}