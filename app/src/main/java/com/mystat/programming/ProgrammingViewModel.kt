package com.mystat.programming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mystat.Months
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ProgrammingViewModel : ViewModel() {

    //VARIABLES ---------------------------------------------------------------------------------------------------

    private val repository = ProgrammingRepository()


    private val _programmingStat = MutableLiveData<List<ProgrammingStat>>()
    val programmingStat: LiveData<List<ProgrammingStat>> = _programmingStat

    private val _programmingStatById = MutableLiveData<ProgrammingStat>()
    val programmingStatById: LiveData<ProgrammingStat> = _programmingStatById


    //All time duration sum --------------------------------------------------------------------------

    //Duration sum all categories
    private val _durationSumAllCategoriesAllTime = MutableLiveData<Int>()
    val durationSumAllCategoriesAllTime: LiveData<Int> = _durationSumAllCategoriesAllTime

    //Duration sum by categories
    private val _durationSumZayAllTime = MutableLiveData<Int>()
    val durationSumZayAllTime: LiveData<Int> = _durationSumZayAllTime
    private val _durationSumMyAppAllTime = MutableLiveData<Int>()
    val durationSumMyAppAllTime: LiveData<Int> = _durationSumMyAppAllTime
    private val _durationSumAnkiAllTime = MutableLiveData<Int>()
    val durationSumAnkiAllTime: LiveData<Int> = _durationSumAnkiAllTime
    private val _durationSumSkillboxAllTime = MutableLiveData<Int>()
    val durationSumSkillboxAllTime: LiveData<Int> = _durationSumSkillboxAllTime
    private val _durationSumPuzzleAllTime = MutableLiveData<Int>()
    val durationSumPuzzleAllTime: LiveData<Int> = _durationSumPuzzleAllTime
    private val _durationSumCommonEducationAllTime = MutableLiveData<Int>()
    val durationSumCommonEducationAllTime: LiveData<Int> = _durationSumCommonEducationAllTime

    //Today duration sum --------------------------------------------------------------------------

    //Today duration sum all categories

    private val _durationSumAllCategoriesToday = MutableLiveData<Int>()
    val durationSumAllCategoriesToday: LiveData<Int> = _durationSumAllCategoriesToday

    //Today duration sum by categories
    private val _durationSumZayToday = MutableLiveData<Int>()
    val durationSumZayToday: LiveData<Int> = _durationSumZayToday
    private val _durationSumMyAppToday = MutableLiveData<Int>()
    val durationSumMyAppToday: LiveData<Int> = _durationSumMyAppToday
    private val _durationSumAnkiToday = MutableLiveData<Int>()
    val durationSumAnkiToday: LiveData<Int> = _durationSumAnkiToday
    private val _durationSumSkillboxToday = MutableLiveData<Int>()
    val durationSumSkillboxToday: LiveData<Int> = _durationSumSkillboxToday
    private val _durationSumPuzzleToday = MutableLiveData<Int>()
    val durationSumPuzzleToday: LiveData<Int> = _durationSumPuzzleToday
    private val _durationSumCommonEducationToday = MutableLiveData<Int>()
    val durationSumCommonEducationToday: LiveData<Int> =
        _durationSumCommonEducationToday

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

    //Average month duration sums for all categories

    private val _durationByAllCategoriesAvByMonth = MutableLiveData<String>()
    val durationByAllCategoriesAvByMonth: LiveData<String> = _durationByAllCategoriesAvByMonth

    //Average year duration sums by categories

    private val _durationZayAvByMonth = MutableLiveData<String>()
    val durationZayAvByMonth: LiveData<String> = _durationZayAvByMonth

    private val _durationMyAppAvByMonth = MutableLiveData<String>()
    val durationMyAppAvByMonth: LiveData<String> = _durationMyAppAvByMonth

    private val _durationAnkiAvByMonth = MutableLiveData<String>()
    val durationAnkiAvByMonth: LiveData<String> = _durationAnkiAvByMonth

    private val _durationSkillboxAvByMonth = MutableLiveData<String>()
    val durationSkillboxAvByMonth: LiveData<String> = _durationSkillboxAvByMonth

    private val _durationPuzzleAvByMonth = MutableLiveData<String>()
    val durationPuzzleAvByMonth: LiveData<String> = _durationPuzzleAvByMonth

    private val _durationCommonEducationAvByMonth = MutableLiveData<String>()
    val durationCommonEducationAvByMonth: LiveData<String> = _durationCommonEducationAvByMonth

    //Average year duration sums ---------------------------------------------------------

    //Average year duration sums for all categories

    private val _durationByAllCategoriesAvByYear = MutableLiveData<String>()
    val durationByAllCategoriesAvByYear: LiveData<String> = _durationByAllCategoriesAvByYear

    //Average year duration sums by categories

    private val _durationZayAvByYear = MutableLiveData<String>()
    val durationZayAvByYear: LiveData<String> = _durationZayAvByYear

    private val _durationMyAppAvByYear = MutableLiveData<String>()
    val durationMyAppAvByYear: LiveData<String> = _durationMyAppAvByYear

    private val _durationAnkiAvByYear = MutableLiveData<String>()
    val durationAnkiAvByYear: LiveData<String> = _durationAnkiAvByYear

    private val _durationSkillboxAvByYear = MutableLiveData<String>()
    val durationSkillboxAvByYear: LiveData<String> = _durationSkillboxAvByYear

    private val _durationPuzzleAvByYear = MutableLiveData<String>()
    val durationPuzzleAvByYear: LiveData<String> = _durationPuzzleAvByYear

    private val _durationCommonEducationAvByYear = MutableLiveData<String>()
    val durationCommonEducationAvByYear: LiveData<String> = _durationCommonEducationAvByYear

    //Average all time duration sums ---------------------------------------------------------

    //Average all time duration sums for all categories

    private val _durationByAllCategoriesAvByAllTime = MutableLiveData<String>()
    val durationByAllCategoriesAvByAllTime: LiveData<String> = _durationByAllCategoriesAvByAllTime

    //Average all time duration sums for selected categories

    private val _durationZayAvByAllTime = MutableLiveData<String>()
    val durationZayAvByAllTime: LiveData<String> = _durationZayAvByAllTime

    private val _durationMyAppAvByAllTime = MutableLiveData<String>()
    val durationMyAppAvByAllTime: LiveData<String> = _durationMyAppAvByAllTime

    private val _durationAnkiAvByAllTime = MutableLiveData<String>()
    val durationAnkiAvByAllTime: LiveData<String> = _durationAnkiAvByAllTime

    private val _durationSkillboxAvByAllTime = MutableLiveData<String>()
    val durationSkillboxAvByAllTime: LiveData<String> = _durationSkillboxAvByAllTime

    private val _durationPuzzleAvByAllTime = MutableLiveData<String>()
    val durationPuzzleAvByAllTime: LiveData<String> = _durationPuzzleAvByAllTime

    private val _durationCommonEducationAvByAllTime = MutableLiveData<String>()
    val durationCommonEducationAvByAllTime: LiveData<String> = _durationCommonEducationAvByAllTime


    //METHODS ---------------------------------------------------------------------------------------------------

    //Add to Firebase database
    fun addToFirebaseDatabase(programmingStat: ProgrammingStatForFirebase){
        repository.add(programmingStat)
    }


    //Adding and getting programming statistic -----------------------------------------------------
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
    fun getProgrammingStatById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _programmingStatById.postValue(repository.getProgrammingStatById(id))
        }
    }

    fun updateDurationStat(id: Long, duration: Int){
        viewModelScope.launch {
            repository.updateDurationStat(id, duration)
        }
    }

    fun updateTypeStat(id: Long, types: ProgrammingTypes){
        viewModelScope.launch {
            repository.updateTypeStat(id, types)
        }
    }

    fun deleteStatElement(id: Long){
        viewModelScope.launch {
            repository.deleteStatElement(id)
        }
    }


    //GET TOTAL SUMS -------------------------------------------------------------------------------

    //total sum for ALL CATEGORIES

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
            val currentYear = LocalDate.now().year.toString()
            val prStat = repository.getProgrammingStat().filter { programmingStat ->
                val year = programmingStat.day.toString().substringBefore('-')
                val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                month == getMonthByName(currentMonth)&&year==currentYear
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

    //total sums for SELECTED categories -----------------------------------------------------------

    //total sums for selected categories current day

    fun getDurationSumByTypesToday(types: ProgrammingTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            when (types) {
                ProgrammingTypes.ZAYCEV -> _durationSumZayToday.postValue(
                    repository.getAllSumByTypeToday(
                        ProgrammingTypes.ZAYCEV
                    )
                )
                ProgrammingTypes.MY_APP -> _durationSumMyAppToday.postValue(
                    repository.getAllSumByTypeToday(
                        ProgrammingTypes.MY_APP
                    )
                )
                ProgrammingTypes.ANKI -> _durationSumAnkiToday.postValue(
                    repository.getAllSumByTypeToday(
                        ProgrammingTypes.ANKI
                    )
                )
                ProgrammingTypes.SKILLBOX -> _durationSumSkillboxToday.postValue(
                    repository.getAllSumByTypeToday(ProgrammingTypes.SKILLBOX)
                )
                ProgrammingTypes.PUZZLE -> _durationSumPuzzleToday.postValue(
                    repository.getAllSumByTypeToday(
                        ProgrammingTypes.PUZZLE
                    )
                )
                ProgrammingTypes.COMMON_EDUCATION -> _durationSumCommonEducationToday.postValue(
                    repository.getAllSumByTypeToday(ProgrammingTypes.COMMON_EDUCATION)
                )
            }
        }
    }

    //total sums for selected categories current month

    fun getDurationSumByTypesThisMonth(types: ProgrammingTypes){
        viewModelScope.launch(Dispatchers.IO) {
            when (types) {

                ProgrammingTypes.ZAYCEV -> {
                    val currentYear = LocalDate.now().year.toString()
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val year = programmingStat.day.toString().substringBefore('-')
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)&&year==currentYear
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.ZAYCEV)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthZay.postValue(sum)
                }

                ProgrammingTypes.MY_APP -> {
                    val currentYear = LocalDate.now().year.toString()
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val year = programmingStat.day.toString().substringBefore('-')
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)&&year==currentYear
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.MY_APP)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthMyApp.postValue(sum)
                }

                ProgrammingTypes.ANKI -> {
                    val currentYear = LocalDate.now().year.toString()
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val year = programmingStat.day.toString().substringBefore('-')
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)&&year==currentYear
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.ANKI)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthAnki.postValue(sum)
                }

                ProgrammingTypes.SKILLBOX -> {
                    val currentYear = LocalDate.now().year.toString()
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val year = programmingStat.day.toString().substringBefore('-')
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)&&year==currentYear
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.SKILLBOX)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthSkillbox.postValue(sum)
                }

                ProgrammingTypes.PUZZLE -> {
                    val currentYear = LocalDate.now().year.toString()
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val year = programmingStat.day.toString().substringBefore('-')
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)&&year==currentYear
                    }
                    var sum = 0
                    prStat.forEach { programmingStat ->
                        if (programmingStat.type == ProgrammingTypes.PUZZLE)
                            sum += programmingStat.durationInMin
                    }
                    _durationSumThisMonthPuzzle.postValue(sum)
                }

                ProgrammingTypes.COMMON_EDUCATION -> {
                    val currentYear = LocalDate.now().year.toString()
                    val currentMonth = LocalDate.now().month.toString()
                    val prStat = repository.getProgrammingStat().filter { programmingStat ->
                        val year = programmingStat.day.toString().substringBefore('-')
                        val month = programmingStat.day.toString().substringBeforeLast('-').substringAfter('-')
                        month == getMonthByName(currentMonth)&&year==currentYear
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

    //total sums for selected categories current year

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
                    Timber.d("test")
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

    //total sums for selected categories all time
    fun getDurationSumByTypesAllTime(types: ProgrammingTypes) {
        Timber.d("test")
        viewModelScope.launch(Dispatchers.IO) {
            when (types) {
                ProgrammingTypes.ZAYCEV -> _durationSumZayAllTime.postValue(
                    repository.getAllSumByType(
                        ProgrammingTypes.ZAYCEV
                    )
                )
                ProgrammingTypes.MY_APP -> _durationSumMyAppAllTime.postValue(
                    repository.getAllSumByType(
                        ProgrammingTypes.MY_APP
                    )
                )
                ProgrammingTypes.ANKI -> _durationSumAnkiAllTime.postValue(
                    repository.getAllSumByType(
                        ProgrammingTypes.ANKI
                    )
                )
                ProgrammingTypes.SKILLBOX -> _durationSumSkillboxAllTime.postValue(
                    repository.getAllSumByType(
                        ProgrammingTypes.SKILLBOX
                    )
                )
                ProgrammingTypes.PUZZLE -> _durationSumPuzzleAllTime.postValue(
                    repository.getAllSumByType(
                        ProgrammingTypes.PUZZLE
                    )
                )
                ProgrammingTypes.COMMON_EDUCATION -> _durationSumCommonEducationAllTime.postValue(
                    repository.getAllSumByType(ProgrammingTypes.COMMON_EDUCATION)
                )
            }
        }
    }


    //methods for AVERAGE duration counting: -------------------------------------------------------

    //average duration counting for all categories:

    //average duration counting by month for all categories

    fun getAverageDurationByMonthAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {

            val currentMonth = LocalDate.now().toString().substringBeforeLast('-').substringAfter('-')
            val thisMonthProgStat = repository.getProgrammingStat().filter { programmingStat ->
                programmingStat.day.toString().substringBeforeLast('-').substringAfter('-') == currentMonth
            }.map { programmingStat ->
                programmingStat.durationInMin
            }.sum()

            val currentDay = LocalDate.now().dayOfMonth

            val averageDayDurationByMonth = thisMonthProgStat.div(currentDay.toDouble())
            val decimalFormat = DecimalFormat("#.##")
            val result: String = decimalFormat.format(averageDayDurationByMonth)
            _durationByAllCategoriesAvByMonth.postValue(result)
        }
    }

    //average duration counting by year for all categories

    fun getAverageDurationByYearAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentYear = LocalDate.now().year.toString()
            val thisYearProgStat = repository.getProgrammingStat().filter { programmingStat ->
                programmingStat.day.toString().take(4) == currentYear
            }
            val currentDay = LocalDate.now().dayOfYear
            val durationSumAllCatThisYear = thisYearProgStat.map { programmingStat ->
                programmingStat.durationInMin
            }.sum()
            val averageDayDurationByYear = durationSumAllCatThisYear.div(currentDay.toDouble())?:0.0
            val decimalFormat = DecimalFormat("#.##")
            val result: String = decimalFormat.format(averageDayDurationByYear)
            _durationByAllCategoriesAvByYear.postValue(result)
        }
    }

    //average duration counting by all time for all categories

    fun getAverageDurationByAllTimeAllCategories() {
        Timber.d("test")
        viewModelScope.launch(Dispatchers.IO) {

            val minStatValue = repository.getProgrammingStat().minByOrNull { programmingStat ->
                programmingStat.day.toString().filter{it != '-'}
            }

            val minDateValue = minStatValue?.day
            val currentDay = LocalDate.now()
            val days: Long? = minDateValue?.until(currentDay, ChronoUnit.DAYS)?.plus(1)

            val durationSumAllCatAllTime = repository.getAllSum()
            val averageDayDurationByAllTime = durationSumAllCatAllTime.div(days?.toDouble()?:1.0)?:0.0
            val decimalFormat = DecimalFormat("#.##")
            val result: String = decimalFormat.format(averageDayDurationByAllTime)
            _durationByAllCategoriesAvByAllTime.postValue(result)
        }
    }

    //average duration counting for selected categories --------------------------------------------

    //average duration counting by month for selected categories

    fun getProgrammingAvDayStatByMonthByType(types: ProgrammingTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentMonth = LocalDate.now().toString().substringBeforeLast('-').substringAfter('-')

            val currentDay = LocalDate.now().dayOfMonth

            when(types){
                ProgrammingTypes.ZAYCEV -> {

                    val thisMonthProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().substringBeforeLast('-').substringAfter('-') == currentMonth && programmingStat.type == ProgrammingTypes.ZAYCEV
                    }.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByMonth = thisMonthProgStat.div(currentDay.toDouble())
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByMonth)
                    _durationZayAvByMonth.postValue(result)
                }
                ProgrammingTypes.MY_APP -> {

                    val thisMonthProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().substringBeforeLast('-').substringAfter('-') == currentMonth && programmingStat.type == ProgrammingTypes.MY_APP
                    }.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByMonth = thisMonthProgStat.div(currentDay.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByMonth)
                    _durationMyAppAvByMonth.postValue(result)
                }
                ProgrammingTypes.ANKI -> {

                    val thisMonthProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().substringBeforeLast('-').substringAfter('-') == currentMonth && programmingStat.type == ProgrammingTypes.ANKI
                    }.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByMonth = thisMonthProgStat.div(currentDay.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByMonth)
                    _durationAnkiAvByMonth.postValue(result)
                }
                ProgrammingTypes.SKILLBOX -> {

                    val thisMonthProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().substringBeforeLast('-').substringAfter('-') == currentMonth && programmingStat.type == ProgrammingTypes.SKILLBOX
                    }.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByMonth = thisMonthProgStat.div(currentDay.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByMonth)
                    _durationSkillboxAvByMonth.postValue(result)
                }
                ProgrammingTypes.PUZZLE -> {

                    val thisMonthProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().substringBeforeLast('-').substringAfter('-') == currentMonth && programmingStat.type == ProgrammingTypes.PUZZLE
                    }.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByMonth = thisMonthProgStat.div(currentDay.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByMonth)
                    _durationPuzzleAvByMonth.postValue(result)
                }
                ProgrammingTypes.COMMON_EDUCATION -> {

                    val thisMonthProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().substringBeforeLast('-').substringAfter('-') == currentMonth && programmingStat.type == ProgrammingTypes.COMMON_EDUCATION
                    }.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByMonth = thisMonthProgStat.div(currentDay.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByMonth)
                    _durationCommonEducationAvByMonth.postValue(result)
                }
            }

        }
    }

    //average duration counting by year for selected categories

    fun getProgrammingAvDayStatByYearByType(types: ProgrammingTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentDay = LocalDate.now().dayOfYear
            val currentYear = LocalDate.now().year.toString()


            when(types){
                ProgrammingTypes.ZAYCEV -> {

                    val thisYearProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear && programmingStat.type == ProgrammingTypes.ZAYCEV
                    }

                    val durationSumAllCatThisYear = thisYearProgStat.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByYear = durationSumAllCatThisYear.div(currentDay.toDouble())
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByYear)
                    _durationZayAvByYear.postValue(result)
                }
                ProgrammingTypes.MY_APP -> {

                    val thisYearProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear && programmingStat.type == ProgrammingTypes.MY_APP
                    }

                    val durationSumAllCatThisYear = thisYearProgStat.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByYear = durationSumAllCatThisYear.div(currentDay.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByYear)
                    _durationMyAppAvByYear.postValue(result)
                }
                ProgrammingTypes.ANKI -> {

                    val thisYearProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear && programmingStat.type == ProgrammingTypes.ANKI
                    }

                    val durationSumAllCatThisYear = thisYearProgStat.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByYear = durationSumAllCatThisYear.div(currentDay.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByYear)
                    _durationAnkiAvByYear.postValue(result)
                }
                ProgrammingTypes.SKILLBOX -> {

                    val thisYearProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear && programmingStat.type == ProgrammingTypes.SKILLBOX
                    }

                    val durationSumAllCatThisYear = thisYearProgStat.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByYear = durationSumAllCatThisYear.div(currentDay.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByYear)
                    _durationSkillboxAvByYear.postValue(result)
                }
                ProgrammingTypes.PUZZLE -> {

                    val thisYearProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear && programmingStat.type == ProgrammingTypes.PUZZLE
                    }

                    val durationSumAllCatThisYear = thisYearProgStat.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByYear = durationSumAllCatThisYear.div(currentDay.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByYear)
                    _durationPuzzleAvByYear.postValue(result)
                }
                ProgrammingTypes.COMMON_EDUCATION -> {

                    val thisYearProgStat = repository.getProgrammingStat().filter { programmingStat ->
                        programmingStat.day.toString().take(4) == currentYear && programmingStat.type == ProgrammingTypes.COMMON_EDUCATION
                    }

                    val durationSumAllCatThisYear = thisYearProgStat.map { programmingStat ->
                        programmingStat.durationInMin
                    }.sum()

                    val averageDayDurationByYear = durationSumAllCatThisYear.div(currentDay.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByYear)
                    _durationCommonEducationAvByYear.postValue(result)
                }
            }
        }
    }

    //average duration counting by all time for selected categories

    fun getProgrammingAvDayStatByAllTimeByType(types: ProgrammingTypes) {
        Timber.d("test")
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("test")
            val minStatValue = repository.getProgrammingStat().minByOrNull { programmingStat ->
                programmingStat.day.toString().filter{it != '-'}
            }

            val minDateValue = minStatValue?.day
            val currentDay = LocalDate.now()
            val days: Long? = minDateValue?.until(currentDay, ChronoUnit.DAYS)?.plus(1)

            when(types){
                ProgrammingTypes.ZAYCEV -> {
                    val averageDayDurationByAllTime = _durationSumZayAllTime.value?.div(days!!.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByAllTime)
                    _durationZayAvByAllTime.postValue(result)
                }
                ProgrammingTypes.MY_APP -> {
                    val averageDayDurationByAllTime = _durationSumMyAppAllTime.value?.div(days!!.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByAllTime)
                    _durationMyAppAvByAllTime.postValue(result)
                }
                ProgrammingTypes.ANKI -> {
                    val averageDayDurationByAllTime = _durationSumAnkiAllTime.value?.div(days!!.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByAllTime)
                    _durationAnkiAvByAllTime.postValue(result)
                }
                ProgrammingTypes.SKILLBOX -> {
                    val averageDayDurationByAllTime = _durationSumSkillboxAllTime.value?.div(days!!.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByAllTime)
                    _durationSkillboxAvByAllTime.postValue(result)
                }
                ProgrammingTypes.PUZZLE -> {
                    val averageDayDurationByAllTime = _durationSumPuzzleAllTime.value?.div(days!!.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByAllTime)
                    _durationPuzzleAvByAllTime.postValue(result)
                }
                ProgrammingTypes.COMMON_EDUCATION -> {
                    val averageDayDurationByAllTime = _durationSumCommonEducationAllTime.value?.div(days!!.toDouble())?:0.0
                    val decimalFormat = DecimalFormat("#.##")
                    val result: String = decimalFormat.format(averageDayDurationByAllTime)
                    _durationCommonEducationAvByAllTime.postValue(result)
                }
            }
        }
    }



    private fun getMonthByName(monthName: String): String{
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