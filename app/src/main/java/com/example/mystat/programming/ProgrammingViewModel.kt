package com.example.mystat.programming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystat.Months
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.time.LocalDate

class ProgrammingViewModel : ViewModel() {

    //VARIABLES ---------------------------------------------------------------------------------------------------

    private val repository = ProgrammingRepository()


    private val _programmingStat = MutableLiveData<List<ProgrammingStat>>()
    val programmingStat: LiveData<List<ProgrammingStat>> = _programmingStat


    //All time duration sum --------------------------------------------------------------------------

    //Duration sum all categories
    private val _durationSumAllCategoriesAllTime = MutableLiveData<Int>()
    val durationSumAllCategoriesAllTime: LiveData<Int> = _durationSumAllCategoriesAllTime

    //Duration sum by categories
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

    //Today duration sum --------------------------------------------------------------------------

    //Today duration sum all categories

    private val _durationSumAllCategoriesToday = MutableLiveData<Int>()
    val durationSumAllCategoriesToday: LiveData<Int> = _durationSumAllCategoriesToday

    //Today duration sum by categories
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
    val programmingStatSumTodayCommonEducation: LiveData<Int> =
        _programmingStatSumTodayCommonEducation

    //This month duration sum -----------------------------------------------------------------------

    //This month duration sum for all categories:

    private val _durationSumAllCategoriesThisMonth = MutableLiveData<Int>()
    val durationSumAllCategoriesThisMonth: LiveData<Int> = _durationSumAllCategoriesThisMonth

    //This month duration sum by categories

    private val _durationSumThisMonthZay = MutableLiveData<Int>()
    val durationSumThisMonthZay: LiveData<Int> = _durationSumThisMonthZay

    private val _durationSumThisMonthMyApp = MutableLiveData<Int>()
    val durationSumThisMonthMyApp: LiveData<Int> = _durationSumThisMonthMyApp

    private val _durationSumThisMonthAnki = MutableLiveData<Int>()
    val durationSumThisMonthAnki: LiveData<Int> = _durationSumThisMonthAnki

    private val _durationSumThisMonthSkillbox = MutableLiveData<Int>()
    val durationSumThisMonthSkillbox: LiveData<Int> = _durationSumThisMonthSkillbox

    private val _durationSumThisMonthPuzzle = MutableLiveData<Int>()
    val durationSumThisMonthPuzzle: LiveData<Int> = _durationSumThisMonthPuzzle

    private val _durationSumThisMonthCommonEd = MutableLiveData<Int>()
    val durationSumThisMonthCommonEd: LiveData<Int> = _durationSumThisMonthCommonEd

    //This year duration sum -----------------------------------------------------------------------

    //This year duration sum for all categories:

    private val _durationSumAllCategoriesThisYear = MutableLiveData<Int>()
    val durationSumAllCategoriesThisYear: LiveData<Int> = _durationSumAllCategoriesThisYear

    //This year duration sum by categories

    private val _durationSumThisYearZay = MutableLiveData<Int>()
    val durationSumThisYearZay: LiveData<Int> = _durationSumThisYearZay

    private val _durationSumThisYearMyApp = MutableLiveData<Int>()
    val durationSumThisYearMyApp: LiveData<Int> = _durationSumThisYearMyApp

    private val _durationSumThisYearAnki = MutableLiveData<Int>()
    val durationSumThisYearAnki: LiveData<Int> = _durationSumThisYearAnki

    private val _durationSumThisYearSkillbox = MutableLiveData<Int>()
    val durationSumThisYearSkillbox: LiveData<Int> = _durationSumThisYearSkillbox

    private val _durationSumThisYearPuzzle = MutableLiveData<Int>()
    val durationSumThisYearPuzzle: LiveData<Int> = _durationSumThisYearPuzzle

    private val _durationSumThisYearCommonEd = MutableLiveData<Int>()
    val durationSumThisYearCommonEd: LiveData<Int> = _durationSumThisYearCommonEd



    //AVERAGE DURATION SUMS  -----------------------------------------------------------------------


    //Average month duration sums --------------------------------------------------------

    //Average year duration sums ---------------------------------------------------------

    //Average year duration sums for all categories

    private val _programmingAvDayStatByYearAllCategories = MutableLiveData<String>()
    val programmingAvDayStatByYearAllCategories: LiveData<String> = _programmingAvDayStatByYearAllCategories

    //Average year duration sums by categories

    private val _programmingAvDayStatByYearZaycevType = MutableLiveData<String>()
    val programmingAvDayStatByYearZaycevType: LiveData<String> = _programmingAvDayStatByYearZaycevType




    //METHODS ---------------------------------------------------------------------------------------------------

    fun addProgrammingStat(programmingStat: List<ProgrammingStat>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProgrammingStat(programmingStat)
        }
    }

    fun getProgrammingStat() {
        viewModelScope.launch(Dispatchers.IO) {
            _programmingStat.postValue(repository.getProgrammingStat())
        }
    }



    //get total sums ------------------------------------------------------------

    //total sum for all categories

    fun getDurationSumForAllCategoriesAllTime() {
        viewModelScope.launch(Dispatchers.IO) {
            _durationSumAllCategoriesAllTime.postValue(repository.getAllSum())
        }
    }

    fun getDurationSumForAllCategoriesToday() {
        viewModelScope.launch(Dispatchers.IO) {
            _durationSumAllCategoriesToday.postValue(repository.getAllSumToday())
        }
    }

    fun getDurationSumForAllCategoriesThisMonth() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentMonth = LocalDate.now().month.toString()
            val prStat = repository.getProgrammingStat().filter { programmingStat ->
                val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                month == getMonthByName(currentMonth)
            }
            var sum = 0
            prStat.forEach { programmingStat ->
                    sum += programmingStat.durationInMin
            }
            _durationSumAllCategoriesThisMonth.postValue(sum)
        }
    }

    fun getDurationSumForAllCategoriesThisYear() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentYear = LocalDate.now().year.toString()
            val prStat = repository.getProgrammingStat().filter { programmingStat ->
                programmingStat.day.toString().take(4) == currentYear
            }
            var sum = 0
            prStat.forEach { programmingStat ->
                sum += programmingStat.durationInMin
            }
            _durationSumAllCategoriesThisYear.postValue(sum)
        }
    }

    //total sums for selected categories

    //all time
    fun getDurationSumByTypesAllTime(types: ProgrammingTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            when (types) {
                ProgrammingTypes.ZAYCEV -> _programmingStatSumZay.postValue(
                    repository.getAllSumByType(
                        ProgrammingTypes.ZAYCEV
                    )
                )
                ProgrammingTypes.MY_APP -> _programmingStatSumMyApp.postValue(
                    repository.getAllSumByType(
                        ProgrammingTypes.MY_APP
                    )
                )
                ProgrammingTypes.ANKI -> _programmingStatSumAnki.postValue(
                    repository.getAllSumByType(
                        ProgrammingTypes.ANKI
                    )
                )
                ProgrammingTypes.SKILLBOX -> _programmingStatSumSkillbox.postValue(
                    repository.getAllSumByType(
                        ProgrammingTypes.SKILLBOX
                    )
                )
                ProgrammingTypes.PUZZLE -> _programmingStatSumPuzzle.postValue(
                    repository.getAllSumByType(
                        ProgrammingTypes.PUZZLE
                    )
                )
                ProgrammingTypes.COMMON_EDUCATION -> _programmingStatSumCommonEducation.postValue(
                    repository.getAllSumByType(ProgrammingTypes.COMMON_EDUCATION)
                )
            }
        }
    }

    //current day

    fun getDurationSumByTypesToday(types: ProgrammingTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            when (types) {
                ProgrammingTypes.ZAYCEV -> _programmingStatSumTodayZay.postValue(
                    repository.getAllSumByTypeToday(
                        ProgrammingTypes.ZAYCEV
                    )
                )
                ProgrammingTypes.MY_APP -> _programmingStatSumTodayMyApp.postValue(
                    repository.getAllSumByTypeToday(
                        ProgrammingTypes.MY_APP
                    )
                )
                ProgrammingTypes.ANKI -> _programmingStatSumTodayAnki.postValue(
                    repository.getAllSumByTypeToday(
                        ProgrammingTypes.ANKI
                    )
                )
                ProgrammingTypes.SKILLBOX -> _programmingStatSumTodaySkillbox.postValue(
                    repository.getAllSumByTypeToday(ProgrammingTypes.SKILLBOX)
                )
                ProgrammingTypes.PUZZLE -> _programmingStatSumTodayPuzzle.postValue(
                    repository.getAllSumByTypeToday(
                        ProgrammingTypes.PUZZLE
                    )
                )
                ProgrammingTypes.COMMON_EDUCATION -> _programmingStatSumTodayCommonEducation.postValue(
                    repository.getAllSumByTypeToday(ProgrammingTypes.COMMON_EDUCATION)
                )
            }
        }
    }

    //current month

    fun getDurationSumByTypesThisMonth(types: ProgrammingTypes){
        viewModelScope.launch(Dispatchers.IO) {
            when (types) {

                ProgrammingTypes.ZAYCEV -> {
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.ZAYCEV)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthZay.postValue(sum)
                }

                ProgrammingTypes.MY_APP -> {
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.MY_APP)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthMyApp.postValue(sum)
                }

                ProgrammingTypes.ANKI -> {
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.ANKI)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthAnki.postValue(sum)
                }

                ProgrammingTypes.SKILLBOX -> {
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.SKILLBOX)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthSkillbox.postValue(sum)
                }

                ProgrammingTypes.PUZZLE -> {
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.PUZZLE)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthPuzzle.postValue(sum)
                }

                ProgrammingTypes.COMMON_EDUCATION -> {
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.COMMON_EDUCATION)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthCommonEd.postValue(sum)
                }
            }
        }
    }

    //current year

    fun getDurationSumByTypesThisYear(types: ProgrammingTypes){
        viewModelScope.launch(Dispatchers.IO) {
            when (types) {

                ProgrammingTypes.ZAYCEV -> {
                    val currentYear = LocalDate.now().year.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                    programmingStat.day.toString().take(4) == currentYear
                }
                    var sum = 0
                prStat.forEach { programmingStat ->
                    if (programmingStat.type == ProgrammingTypes.ZAYCEV)
                    sum += programmingStat.durationInMin
                }
                    _durationSumThisYearZay.postValue(sum)
                }

                ProgrammingTypes.MY_APP -> {
                    val currentYear = LocalDate.now().year.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.MY_APP)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisYearMyApp.postValue(sum)
                }

                ProgrammingTypes.ANKI -> {
                    val currentYear = LocalDate.now().year.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.ANKI)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisYearAnki.postValue(sum)
                }

                ProgrammingTypes.SKILLBOX -> {
                    val currentYear = LocalDate.now().year.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.SKILLBOX)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisYearSkillbox.postValue(sum)
                }

                ProgrammingTypes.PUZZLE -> {
                    val currentYear = LocalDate.now().year.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.PUZZLE)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisYearPuzzle.postValue(sum)
                }

                ProgrammingTypes.COMMON_EDUCATION -> {
                    val currentYear = LocalDate.now().year.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.COMMON_EDUCATION)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisYearCommonEd.postValue(sum)
                }
            }
        }
    }


    //methods for average duration counting: -------------------------------------------------------

    //average duration counting for all categories:

    fun getProgrammingAvDayStatByYearAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {

            val max = repository.getProgrammingStat().maxByOrNull { programmingStat ->
                programmingStat.day.toString().filter{it != '-'}
            }

            val maxDateValue = max?.day

            val dayOfYear = maxDateValue?.dayOfYear?:0
            val averageDayDurationByYear = _durationSumAllCategoriesAllTime.value?.div(dayOfYear.toDouble())?:0.0
            val decimalFormat = DecimalFormat("#.##")
            val result: String = decimalFormat.format(averageDayDurationByYear)
            _programmingAvDayStatByYearAllCategories.postValue(result)
        }
    }

    fun getProgrammingAvDayStatByYearByType(types: ProgrammingTypes) {
        viewModelScope.launch(Dispatchers.IO) {

            val max = repository.getProgrammingStat().maxByOrNull { programmingStat ->
                programmingStat.day.toString().filter{it != '-'}
            }

            val maxDateValue = max?.day

            val dayOfYear = maxDateValue?.dayOfYear?:0
            when(types){
                ProgrammingTypes.ZAYCEV -> {
                    val averageDayDurationByYear = _programmingStatSumZay.value?.div(dayOfYear.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByYear)
                    _programmingAvDayStatByYearZaycevType.postValue(result)
                }
            }

        }
    }

    fun getMonthByName(monthName: String): String{
        return when(monthName){
            Months.JANUARY.name -> "01"
            Months.FEBRUARY.name -> "02"
            Months.MARCH.name -> "03"
            Months.APRIL.name -> "04"
            Months.MAY.name -> "05"
            Months.JUNE.name -> "06"
            Months.JULY.name -> "07"
            Months.AUGUST.name -> "08"
            Months.SEPTEMBER.name -> "09"
            Months.OCTOBER.name -> "10"
            Months.NOVEMBER.name -> "11"
            Months.DECEMBER.name -> "12"
            else -> "0"
        }
    }


}