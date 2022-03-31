package com.mystat.programming

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.auth.FirebaseAuth
import com.mystat.R
import com.mystat.databinding.FragmentProgrammingBinding
import com.mystat.utils.getProgrammingTypesFromRadioGroup
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener







class ProgrammingFragment : Fragment(R.layout.fragment_programming) {

    private val binding: FragmentProgrammingBinding by viewBinding(FragmentProgrammingBinding::bind)
    private val viewModel: ProgrammingViewModel by viewModels()
    private var programmingTypesAdding: ProgrammingTypes = ProgrammingTypes.SKILLBOX
    private var isDayButtonClicked = true
    private var isMonthButtonClicked = false
    private var isYearButtonClicked = false
    private var isAllButtonClicked = false
    private var isAvMonthButtonClicked = false
    private var isAvYearButtonClicked = false
    private var isAvAllButtonClicked = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTotalDurationThisDay()



//        // Read from the database
//        myRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue(ProgrammingStatForFirebase::class.java)
//                Log.d("TAG", "Value is: $value")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//                Log.w("TAG", "Failed to read value.", error.toException())
//            }
//        })

        lifecycleScope.launchWhenCreated {

            binding.radioGroup.getProgrammingTypesFromRadioGroup().collect { types ->
                programmingTypesAdding = types
            }
        }

        binding.buttonList.setOnClickListener {
            findNavController().navigate(R.id.action_programmingFragment_to_programmingListStatFragment)
        }

        binding.buttonOk.setOnClickListener {
            viewModel.getFromFB()
            val duration = binding.editText.text.toString().toIntOrNull()
            val programmingStat = listOf(
                ProgrammingStat(
                    id = 0,
                    durationInMin = duration ?: 0,
                    type = programmingTypesAdding,
                    uid = FirebaseAuth.getInstance().currentUser?.uid?:"emptyUid"
                )
            )
            viewModel.apply {
                addProgrammingStat(programmingStat)
                getTotalDurationThisDay()
                getDurationSumForAllCategoriesAllTime()
                ProgrammingTypes.values().forEach {
                    getDurationSumByTypesAllTime(it)
                }
            }

            //add to firebase

            viewModel.apply {
                addToFirebaseDatabase(
                    ProgrammingStatForFirebase(
                    durationInMin = duration ?: 0,
                    type = programmingTypesAdding
                ))
            }

        }

        //Total sum buttons click ---------------------------------------------

        binding.buttonDay.setOnClickListener {
            changeButtonColor(ButtonTypes.DAY)
            getTotalDurationThisDay()
        }

        binding.buttonMonth.setOnClickListener {
            changeButtonColor(ButtonTypes.MONTH)
            getTotalDurationAllTime()
            getTotalDurationThisMonth()
            getAverageDurationThisMonth()
        }

        binding.buttonYear.setOnClickListener {
            changeButtonColor(ButtonTypes.YEAR)
            getTotalDurationAllTime()
            getTotalDurationThisYear()
            getAverageDurationThisYear()
        }

        binding.buttonAll.setOnClickListener {
            changeButtonColor(ButtonTypes.ALL)
            getTotalDurationAllTime()
            getAverageDurationAllTime()
        }

        //Observing --------------------------------------------------------

        observe()
    }


    private fun getTotalDurationThisDay() {
        viewModel.apply {
            getDurationSumForAllCategoriesToday()
            ProgrammingTypes.values().forEach {
                getDurationSumByTypesToday(it)
            }
        }
    }

    private fun getAverageDurationThisMonth() {
        viewModel.apply {
            getAverageDurationByMonthAllCategories()
            ProgrammingTypes.values().forEach {
                getProgrammingAvDayStatByMonthByType(it)
            }
        }
    }

    private fun getTotalDurationThisMonth() {
        viewModel.apply {
            getDurationSumForAllCategoriesThisMonth()
            ProgrammingTypes.values().forEach {
                getDurationSumByTypesThisMonth(it)
            }
        }
    }

    private fun getTotalDurationThisYear() {
        viewModel.apply {
            getDurationSumForAllCategoriesThisYear()
            ProgrammingTypes.values().forEach {
                getDurationSumByTypesThisYear(it)
            }
        }
    }

    private fun getAverageDurationThisYear() {
        viewModel.apply {
            getAverageDurationByYearAllCategories()
            ProgrammingTypes.values().forEach {
                getProgrammingAvDayStatByYearByType(it)
            }
        }
    }

    private fun getTotalDurationAllTime() {
        viewModel.apply {
            getDurationSumForAllCategoriesAllTime()
            ProgrammingTypes.values().forEach {
                getDurationSumByTypesAllTime(it)
            }
        }
    }

    private fun getAverageDurationAllTime() {
        viewModel.apply {
            getAverageDurationByAllTimeAllCategories()
            ProgrammingTypes.values().forEach {
                getProgrammingAvDayStatByAllTimeByType(it)
            }
        }
    }

    private fun observe() {

        viewModel.programmStatsFromFB.observe(viewLifecycleOwner){progrFB ->
            Timber.d("programmFB = $progrFB")
        }

        //Total stat ----------------------------------------------------------------

        observeStatAllCategories()
        observeStatSelectedCategories()

        //Average stat -----------------------------------------------------------------------------

        observeAverageStatAllCategories()
        observeAverageStatSelectedCategories()
    }


    private fun observeStatSelectedCategories() {
        observeStatSelectedCategoriesAllTime()
        observeStatSelectedCategoriesToday()
        observeStatSelectedCategoriesThisYear()
        observeStatSelectedCategoriesThisMonth()
    }

    private fun observeStatAllCategories() {
        observeStatAllCategoriesAllTime()
        observeStatAllCategoriesToday()
        observeStatAllCategoriesThisYear()
        observeStatAllCategoriesThisMonth()
    }

    private fun observeStatSelectedCategoriesAllTime() {
        viewModel.durationSumZayAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvZaycevValueTotalAll.text = sum.toString()
            } else {
                binding.tvZaycevValueTotalAll.text = "0"
            }
        }

        viewModel.durationSumSkillboxAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvSkillboxValueAll.text = sum.toString()
            } else {
                binding.tvSkillboxValueAll.text = "0"
            }
        }

        viewModel.durationSumAnkiAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAnkiValueAll.text = sum.toString()
            } else {
                binding.tvAnkiValueAll.text = "0"
            }
        }

        viewModel.durationSumMyAppAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvMyAppValueAll.text = sum.toString()
            } else {
                binding.tvMyAppValueAll.text = "0"
            }
        }

        viewModel.durationSumCommonEducationAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvCommonEducationValueAll.text = sum.toString()
            } else {
                binding.tvCommonEducationValueAll.text = "0"
            }
        }

        viewModel.durationSumPuzzleAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvPuzzleValueAll.text = sum.toString()
            } else {
                binding.tvPuzzleValueAll.text = "0"
            }
        }
    }

    private fun observeStatAllCategoriesAllTime() {
        viewModel.durationSumAllCategoriesAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAllValueAll.text = sum.toString()
            } else {
                binding.tvAllValueAll.text = "0"
            }
        }
    }

    private fun observeStatSelectedCategoriesToday() {
        viewModel.durationSumZayToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvZaycevValueTotalDay.text = sum.toString()
            } else {
                binding.tvZaycevValueTotalDay.text = "0"
            }

        }

        viewModel.durationSumSkillboxToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvSkillboxValueDay.text = sum.toString()
            } else {
                binding.tvSkillboxValueDay.text = "0"
            }

        }

        viewModel.durationSumAnkiToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAnkiValueDay.text = sum.toString()
            } else {
                binding.tvAnkiValueDay.text = "0"
            }
        }

        viewModel.durationSumMyAppToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvMyAppValueDay.text = sum.toString()
            } else {
                binding.tvMyAppValueDay.text = "0"
            }
        }

        viewModel.durationSumPuzzleToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvPuzzleValueDay.text = sum.toString()
            } else {
                binding.tvPuzzleValueDay.text = "0"
            }
        }

        viewModel.durationSumCommonEducationToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvCommonEducationValueDay.text = sum.toString()
            } else {

                binding.tvCommonEducationValueDay.text = "0"
            }
        }
    }

    private fun observeStatAllCategoriesToday() {
        viewModel.durationSumAllCategoriesToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                Timber.d("all cat today sum1 = $sum")
                binding.tvAllValueDay.text = sum.toString()
            } else {
                binding.tvAllValueDay.text = "0"
            }
        }
    }

    private fun observeStatSelectedCategoriesThisYear() {
        viewModel.durationSumThisYearZay.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvZaycevValueTotalYear.text = sum.toString()
            } else {
                binding.tvZaycevValueTotalYear.text = "0"
            }
        }

        viewModel.durationSumThisYearMyApp.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvMyAppValueYear.text = sum.toString()
            } else {
                binding.tvMyAppValueYear.text = "0"
            }
        }

        viewModel.durationSumThisYearAnki.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAnkiValueYear.text = sum.toString()
            } else {
                binding.tvAnkiValueYear.text = "0"
            }
        }

        viewModel.durationSumThisYearSkillbox.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvSkillboxValueYear.text = sum.toString()
            } else {
                binding.tvSkillboxValueYear.text = "0"
            }
        }

        viewModel.durationSumThisYearPuzzle.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvPuzzleValueYear.text = sum.toString()
            } else {
                binding.tvPuzzleValueYear.text = "0"
            }
        }

        viewModel.durationSumThisYearCommonEd.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvCommonEducationValueYear.text = sum.toString()
            } else {
                binding.tvCommonEducationValueYear.text = "0"
            }
        }
    }

    private fun observeStatAllCategoriesThisYear() {
        viewModel.durationSumAllCategoriesThisYear.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAllValueYear.text = sum.toString()
            } else {
                binding.tvAllValueYear.text = "0"
            }
        }
    }

    private fun observeStatAllCategoriesThisMonth() {
        viewModel.durationSumAllCategoriesThisMonth.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAllValueMonth.text = sum.toString()
            } else {
                binding.tvAllValueMonth.text = "0"
            }
        }
    }

    private fun observeStatSelectedCategoriesThisMonth() {
        viewModel.durationSumThisMonthZay.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvZaycevValueTotalMonth.text = sum.toString()
            } else {
                binding.tvZaycevValueTotalMonth.text = "0"
            }
        }

        viewModel.durationSumThisMonthMyApp.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvMyAppValueMonth.text = sum.toString()
            } else {
                binding.tvMyAppValueMonth.text = "0"
            }
        }

        viewModel.durationSumThisMonthAnki.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAnkiValueMonth.text = sum.toString()
            } else {
                binding.tvAnkiValueMonth.text = "0"
            }
        }

        viewModel.durationSumThisMonthSkillbox.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvSkillboxValueMonth.text = sum.toString()
            } else {
                binding.tvSkillboxValueMonth.text = "0"
            }
        }

        viewModel.durationSumThisMonthPuzzle.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvPuzzleValueMonth.text = sum.toString()
            } else {
                binding.tvPuzzleValueMonth.text = "0"
            }
        }

        viewModel.durationSumThisMonthCommonEd.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvCommonEducationValueMonth.text = sum.toString()
            } else {
                binding.tvCommonEducationValueMonth.text = "0"
            }
        }
    }

    private fun observeAverageStatSelectedCategories() {
        observeAverageStatSelectedCategoriesThisMonth()

        observeAverageStatSelectedCategoriesThisYear()

        observeAverageStatSelectedCategoriesAllTime()
    }

    private fun observeAverageStatAllCategories() {
        observeAverageStatAllCategoriesThisMonth()

        observeAverageStatAllCategoriesAllTime()

        observeAverageStatAllCategoriesThisYear()
    }

    private fun observeAverageStatAllCategoriesThisYear() {
        viewModel.durationByAllCategoriesAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvAllCategoriesValueAverageYear.text = sum
        }
    }

    private fun observeAverageStatAllCategoriesAllTime() {
        viewModel.durationByAllCategoriesAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvAllCategoriesValueAverageAll.text = sum
        }
    }

    private fun observeAverageStatAllCategoriesThisMonth() {
        viewModel.durationByAllCategoriesAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvAllCategoriesValueAverageMonth.text = sum
        }
    }

    private fun observeAverageStatSelectedCategoriesThisMonth() {
        viewModel.durationZayAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvZaycevValueAverageMonth.text = sum
        }

        viewModel.durationMyAppAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvMyAppValueAverageMonth.text = sum
        }

        viewModel.durationAnkiAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvAnkiValueAverageMonth.text = sum
        }

        viewModel.durationSkillboxAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvSkillboxValueAverageMonth.text = sum
        }

        viewModel.durationPuzzleAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvPuzzleValueAverageMonth.text = sum
        }

        viewModel.durationCommonEducationAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvCommonEducationValueAverageMonth.text = sum
        }
    }

    private fun changeButtonColor(types: ButtonTypes) {
        when (types) {
            ButtonTypes.DAY -> {
                isDayButtonClicked = true
                isMonthButtonClicked = false
                isYearButtonClicked = false
                isAllButtonClicked = false
                isAvMonthButtonClicked = false
                isAvYearButtonClicked = false
                isAvAllButtonClicked = false
            }
            ButtonTypes.MONTH -> {
                isDayButtonClicked = false
                isMonthButtonClicked = true
                isYearButtonClicked = false
                isAllButtonClicked = false
                isAvMonthButtonClicked = false
                isAvYearButtonClicked = false
                isAvAllButtonClicked = false
            }
            ButtonTypes.YEAR -> {
                isDayButtonClicked = false
                isMonthButtonClicked = false
                isYearButtonClicked = true
                isAllButtonClicked = false
                isAvMonthButtonClicked = false
                isAvYearButtonClicked = false
                isAvAllButtonClicked = false
            }
            ButtonTypes.ALL -> {
                isDayButtonClicked = false
                isMonthButtonClicked = false
                isYearButtonClicked = false
                isAllButtonClicked = true
                isAvMonthButtonClicked = false
                isAvYearButtonClicked = false
                isAvAllButtonClicked = false
            }

            ButtonTypes.AV_MONTH -> {
                isDayButtonClicked = false
                isMonthButtonClicked = false
                isYearButtonClicked = false
                isAllButtonClicked = false
                isAvMonthButtonClicked = true
                isAvYearButtonClicked = false
                isAvAllButtonClicked = false
            }

            ButtonTypes.AV_YEAR -> {
                isDayButtonClicked = false
                isMonthButtonClicked = false
                isYearButtonClicked = false
                isAllButtonClicked = false
                isAvMonthButtonClicked = false
                isAvYearButtonClicked = true
                isAvAllButtonClicked = false
            }

            ButtonTypes.AV_ALL -> {
                isDayButtonClicked = false
                isMonthButtonClicked = false
                isYearButtonClicked = false
                isAllButtonClicked = false
                isAvMonthButtonClicked = false
                isAvYearButtonClicked = false
                isAvAllButtonClicked = true
            }
        }
        if (isDayButtonClicked) {
            binding.buttonDay.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.dayResultsTable.isVisible = true
            binding.tvResultsTitle.text = "Results of the day"
        } else {
            binding.dayResultsTable.isVisible = false
            binding.buttonDay.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isMonthButtonClicked) {
            binding.monthResultsTable.isVisible = true
            binding.buttonMonth.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Results of the month"
        } else {
            binding.monthResultsTable.isVisible = false
            binding.buttonMonth.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isYearButtonClicked) {
            binding.yearResultsTable.isVisible = true
            binding.buttonYear.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Results of the year"
        } else {
            binding.yearResultsTable.isVisible = false
            binding.buttonYear.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isAllButtonClicked) {
            binding.allTimeResultsTable.isVisible = true
            binding.buttonAll.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Results of all time"
        } else {
            binding.allTimeResultsTable.isVisible = false
            binding.buttonAll.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }
    }

    private fun observeAverageStatSelectedCategoriesAllTime() {
        viewModel.durationZayAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvZaycevValueAverageAll.text = sum
        }

        viewModel.durationMyAppAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvMyAppValueAverageAll.text = sum
        }

        viewModel.durationAnkiAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvAnkiValueAverageAll.text = sum
        }

        viewModel.durationSkillboxAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvSkillboxValueAverageAll.text = sum
        }

        viewModel.durationPuzzleAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvPuzzleValueAverageAll.text = sum
        }

        viewModel.durationCommonEducationAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvCommonEducationValueAverageAll.text = sum
        }
    }

    private fun observeAverageStatSelectedCategoriesThisYear() {
        viewModel.durationZayAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvZaycevValueAverageYear.text = sum
        }

        viewModel.durationMyAppAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvMyAppValueAverageYear.text = sum
        }

        viewModel.durationAnkiAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvAnkiValueAverageYear.text = sum
        }

        viewModel.durationSkillboxAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvSkillboxValueAverageYear.text = sum
        }

        viewModel.durationPuzzleAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvPuzzleValueAverageYear.text = sum
        }

        viewModel.durationCommonEducationAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvCommonEducationValueAverageYear.text = sum
        }
    }
}