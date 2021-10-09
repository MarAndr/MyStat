package com.example.mystat.utils

import android.widget.RadioGroup
import com.example.mystat.R
import com.example.mystat.programming.ProgrammingTypes
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun RadioGroup.getProgrammingTypesFromRadioGroup(): Flow<ProgrammingTypes> {
    return callbackFlow {
        val listener = RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radioButton_zaycev -> sendBlocking(ProgrammingTypes.ZAYCEV)
                R.id.radioButton_myApp -> sendBlocking(ProgrammingTypes.MY_APP)
                R.id.radioButton_skillbox -> sendBlocking(ProgrammingTypes.SKILLBOX)
                R.id.radioButton_anki -> sendBlocking(ProgrammingTypes.ANKI)
                R.id.radioButton_puzzle -> sendBlocking(ProgrammingTypes.PUZZLE)
                R.id.radioButton_commonEducation -> sendBlocking(ProgrammingTypes.COMMON_EDUCATION)
            }
        }

        this@getProgrammingTypesFromRadioGroup.setOnCheckedChangeListener(listener)
        awaitClose {
            setOnCheckedChangeListener(null)
        }

    }
}