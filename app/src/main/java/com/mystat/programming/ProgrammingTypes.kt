package com.mystat.programming

enum class ProgrammingTypes{
    ZAYCEV,
    MY_APP,
    ANKI,
    SKILLBOX,
    PUZZLE,
    COMMON_EDUCATION;

    companion object{
        fun getTypes() = values()
    }
}
