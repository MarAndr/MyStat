package com.example.mystat.programming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProgrammingViewModel: ViewModel() {

    private val repository = ProgrammingRepository()
    private val _programmingStat = MutableLiveData<List<ProgrammingStat>>()
    val programmingStat: LiveData<List<ProgrammingStat>> = _programmingStat
    private val _programmingStatSum = MutableLiveData<Int>()
    val programmingStatSum: LiveData<Int> = _programmingStatSum
    private val _programmingStatSumToday = MutableLiveData<Int>()
    val programmingStatSumToday: LiveData<Int> = _programmingStatSumToday

    //All sum
    private val _programmingStatSumZay = MutableLiveData<Int>()
    val programmingStatSumZay: LiveData<Int> = _programmingStatSumZay
    private val _programmingStatSumMyApp = MutableLiveData<Int>()
    val programmingStatSumMyApp: LiveData<Int> = _programmingStatSumMyApp
    private val _programmingStatSumAnki = MutableLiveData<Int>()
    val programmingStatSumAnki: LiveData<Int> = _programmingStatSumAnki
    private val _programmingStatSumSkillbox = MutableLiveData<Int>()
    val programmingStatSumSkillbox: LiveData<Int> = _programmingStatSumSkillbox
    private val _programmingStatSumPuzzle = MutableLiveData<Int>()
    val programmingStatSumPuzzle: LiveData<Int> = _programmingStatSumPuzzle
    private val _programmingStatSumCommonEducation = MutableLiveData<Int>()
    val programmingStatSumCommonEducation: LiveData<Int> = _programmingStatSumCommonEducation


    //Sum today
    private val _programmingStatSumTodayZay = MutableLiveData<Int>()
    val programmingStatSumTodayZay: LiveData<Int> = _programmingStatSumTodayZay
    private val _programmingStatSumTodayMyApp = MutableLiveData<Int>()
    val programmingStatSumTodayMyApp: LiveData<Int> = _programmingStatSumTodayMyApp
    private val _programmingStatSumTodayAnki = MutableLiveData<Int>()
    val programmingStatSumTodayAnki: LiveData<Int> = _programmingStatSumTodayAnki
    private val _programmingStatSumTodaySkillbox = MutableLiveData<Int>()
    val programmingStatSumTodaySkillbox: LiveData<Int> = _programmingStatSumTodaySkillbox
    private val _programmingStatSumTodayPuzzle = MutableLiveData<Int>()
    val programmingStatSumTodayPuzzle: LiveData<Int> = _programmingStatSumTodayPuzzle
    private val _programmingStatSumTodayCommonEducation = MutableLiveData<Int>()
    val programmingStatSumTodayCommonEducation: LiveData<Int> = _programmingStatSumTodayCommonEducation

    fun addProgrammingStat(programmingStat: List<ProgrammingStat>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProgrammingStat(programmingStat)
        }
    }

    fun getProgrammingStat(){
        viewModelScope.launch(Dispatchers.IO) {
            _programmingStat.postValue(repository.getProgrammingStat())
        }
    }

    fun getAllCategoriesDurationSum(){
        viewModelScope.launch(Dispatchers.IO) {
            _programmingStatSumToday.postValue(repository.getAllSum())
        }
    }

    fun getAllCategoriesDurationSumToday(){
        viewModelScope.launch(Dispatchers.IO) {
        _programmingStatSumToday.postValue(repository.getAllSumToday())
        }
    }

    fun getProgrammingStatSumByTypes(types: ProgrammingTypes){
        viewModelScope.launch(Dispatchers.IO) {
            when(types){
                ProgrammingTypes.ZAYCEV -> _programmingStatSumZay.postValue(repository.getAllSumByType(ProgrammingTypes.ZAYCEV))
                ProgrammingTypes.MY_APP -> _programmingStatSumMyApp.postValue(repository.getAllSumByType(ProgrammingTypes.MY_APP))
                ProgrammingTypes.ANKI -> _programmingStatSumAnki.postValue(repository.getAllSumByType(ProgrammingTypes.ANKI))
                ProgrammingTypes.SKILLBOX -> _programmingStatSumSkillbox.postValue(repository.getAllSumByType(ProgrammingTypes.SKILLBOX))
                ProgrammingTypes.PUZZLE -> _programmingStatSumPuzzle.postValue(repository.getAllSumByType(ProgrammingTypes.PUZZLE))
                ProgrammingTypes.COMMON_EDUCATION -> _programmingStatSumCommonEducation.postValue(repository.getAllSumByType(ProgrammingTypes.COMMON_EDUCATION))
            }
        }
    }

    fun getProgrammingStatSumByTypesToday(types: ProgrammingTypes){
        viewModelScope.launch(Dispatchers.IO) {
            when(types){
                ProgrammingTypes.ZAYCEV -> _programmingStatSumTodayZay.postValue(repository.getAllSumByTypeToday(ProgrammingTypes.ZAYCEV))
                ProgrammingTypes.MY_APP -> _programmingStatSumTodayMyApp.postValue(repository.getAllSumByTypeToday(ProgrammingTypes.MY_APP))
                ProgrammingTypes.ANKI -> _programmingStatSumTodayAnki.postValue(repository.getAllSumByTypeToday(ProgrammingTypes.ANKI))
                ProgrammingTypes.SKILLBOX -> _programmingStatSumTodaySkillbox.postValue(repository.getAllSumByTypeToday(ProgrammingTypes.SKILLBOX))
                ProgrammingTypes.PUZZLE -> _programmingStatSumTodayPuzzle.postValue(repository.getAllSumByTypeToday(ProgrammingTypes.PUZZLE))
                ProgrammingTypes.COMMON_EDUCATION -> _programmingStatSumTodayCommonEducation.postValue(repository.getAllSumByTypeToday(ProgrammingTypes.COMMON_EDUCATION))
            }
        }
    }


}