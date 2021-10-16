package com.example.mystat.programming

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mystat.R
import com.example.mystat.databinding.FragmentProgrammingBinding
import com.example.mystat.utils.getProgrammingTypesFromRadioGroup
import kotlinx.coroutines.flow.collect

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
        addStrikesInsteadAverageValues()
        viewModel.apply {
            getDurationSumForAllCategoriesToday()
            getDurationSumForAllCategoriesAllTime()
            ProgrammingTypes.values().forEach {
                getDurationSumByTypesAllTime(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            binding.radioGroup.getProgrammingTypesFromRadioGroup().collect {
                programmingTypesAdding = it
            }
        }

        binding.buttonList.setOnClickListener {
            findNavController().navigate(R.id.action_programmingFragment_to_programmingListStatFragment)
        }

        binding.buttonOk.setOnClickListener {

            val duration = binding.editText.text.toString().toIntOrNull()
            val programmingStat = listOf(
                ProgrammingStat(
                    id = 0,
                    durationInMin = duration ?: 0,
                    type = programmingTypesAdding
                )
            )
            viewModel.apply {
                addProgrammingStat(programmingStat)
                getDurationSumForAllCategoriesAllTime()
                ProgrammingTypes.values().forEach {
                    getDurationSumByTypesAllTime(it)
                }
            }

        }

        //Total sum buttons click ---------------------------------------------

        binding.buttonDay.setOnClickListener {
            changeButtonColor(ButtonTypes.DAY)
            getTotalDurationThisDay()
            addStrikesInsteadAverageValues()
        }

        binding.buttonMonth.setOnClickListener {
            changeButtonColor(ButtonTypes.MONTH)
            getTotalDurationThisMonth()
            getAverageDurationThisMonth()
        }

        binding.buttonYear.setOnClickListener {
            changeButtonColor(ButtonTypes.YEAR)
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

    private fun addStrikesInsteadAverageValues() {
        binding.tvZaycevValueAverage.text = "-"
        binding.tvMyAppValueAverage.text = "-"
        binding.tvAnkiValueAverage.text = "-"
        binding.tvSkillboxValueAverage.text = "-"
        binding.tvPuzzleValueAverage.text = "-"
        binding.tvCommonEducationValueAverage.text = "-"
        binding.tvAllCategoriesValueAverage.text = "-"
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
                binding.tvZaycevValueTotal.text = sum.toString()
            } else {
                binding.tvZaycevValueTotal.text = "0"
            }
        }

        viewModel.durationSumSkillboxAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvSkillboxValue.text = sum.toString()
            } else {
                binding.tvSkillboxValue.text = "0"
            }
        }

        viewModel.durationSumAnkiAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAnkiValue.text = sum.toString()
            } else {
                binding.tvAnkiValue.text = "0"
            }
        }

        viewModel.durationSumMyAppAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvMyAppValue.text = sum.toString()
            } else {
                binding.tvMyAppValue.text = "0"
            }
        }

        viewModel.durationSumCommonEducationAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvCommonEducationValue.text = sum.toString()
            } else {
                binding.tvCommonEducationValue.text = "0"
            }
        }

        viewModel.durationSumPuzzleAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvPuzzleValue.text = sum.toString()
            } else {
                binding.tvPuzzleValue.text = "0"
            }
        }
    }

    private fun observeStatAllCategoriesAllTime() {
        viewModel.durationSumAllCategoriesAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAllValue.text = sum.toString()
            } else {
                binding.tvAllValue.text = "0"
            }
        }
    }

    private fun observeStatSelectedCategoriesToday() {
        viewModel.durationSumZayToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvZaycevValueTotal.text = sum.toString()
            } else {
                binding.tvZaycevValueTotal.text = "0"
            }

        }

        viewModel.durationSumSkillboxToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvSkillboxValue.text = sum.toString()
            } else {
                binding.tvSkillboxValue.text = "0"
            }

        }

        viewModel.durationSumAnkiToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAnkiValue.text = sum.toString()
            } else {
                binding.tvAnkiValue.text = "0"
            }
        }

        viewModel.durationSumMyAppToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvMyAppValue.text = sum.toString()
            } else {
                binding.tvMyAppValue.text = "0"
            }
        }

        viewModel.durationSumPuzzleToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvPuzzleValue.text = sum.toString()
            } else {
                binding.tvPuzzleValue.text = "0"
            }
        }

        viewModel.durationSumCommonEducationToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvCommonEducationValue.text = sum.toString()
            } else {

                binding.tvCommonEducationValue.text = "0"
            }
        }
    }

    private fun observeStatAllCategoriesToday() {
        viewModel.durationSumAllCategoriesToday.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAllValue.text = sum.toString()
            } else {
                binding.tvAllValue.text = "0"
            }
        }
    }

    private fun observeStatSelectedCategoriesThisYear() {
        viewModel.durationSumThisYearZay.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvZaycevValueTotal.text = sum.toString()
            } else {
                binding.tvZaycevValueTotal.text = "0"
            }
        }

        viewModel.durationSumThisYearMyApp.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvMyAppValue.text = sum.toString()
            } else {
                binding.tvMyAppValue.text = "0"
            }
        }

        viewModel.durationSumThisYearAnki.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAnkiValue.text = sum.toString()
            } else {
                binding.tvAnkiValue.text = "0"
            }
        }

        viewModel.durationSumThisYearSkillbox.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvSkillboxValue.text = sum.toString()
            } else {
                binding.tvSkillboxValue.text = "0"
            }
        }

        viewModel.durationSumThisYearPuzzle.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvPuzzleValue.text = sum.toString()
            } else {
                binding.tvPuzzleValue.text = "0"
            }
        }

        viewModel.durationSumThisYearCommonEd.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvCommonEducationValueAverage.text = sum.toString()
            } else {
                binding.tvCommonEducationValueAverage.text = "0"
            }
        }
    }

    private fun observeStatAllCategoriesThisYear() {
        viewModel.durationSumAllCategoriesThisYear.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAllValue.text = sum.toString()
            } else {
                binding.tvAllValue.text = "0"
            }
        }
    }

    private fun observeStatAllCategoriesThisMonth() {
        viewModel.durationSumAllCategoriesThisMonth.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAllValue.text = sum.toString()
            } else {
                binding.tvAllValue.text = "0"
            }
        }
    }

    private fun observeStatSelectedCategoriesThisMonth() {
        viewModel.durationSumThisMonthZay.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvZaycevValueTotal.text = sum.toString()
            } else {
                binding.tvZaycevValueTotal.text = "0"
            }
        }

        viewModel.durationSumThisMonthMyApp.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvMyAppValue.text = sum.toString()
            } else {
                binding.tvMyAppValue.text = "0"
            }
        }

        viewModel.durationSumThisMonthAnki.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvAnkiValue.text = sum.toString()
            } else {
                binding.tvAnkiValue.text = "0"
            }
        }

        viewModel.durationSumThisMonthSkillbox.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvSkillboxValue.text = sum.toString()
            } else {
                binding.tvSkillboxValue.text = "0"
            }
        }

        viewModel.durationSumThisMonthPuzzle.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvPuzzleValue.text = sum.toString()
            } else {
                binding.tvPuzzleValue.text = "0"
            }
        }

        viewModel.durationSumThisMonthCommonEd.observe(viewLifecycleOwner) { sum ->
            if (sum != null) {
                binding.tvCommonEducationValue.text = sum.toString()
            } else {
                binding.tvCommonEducationValue.text = "0"
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
        viewModel.durationByAllCategoriesAvByYear.observe(viewLifecycleOwner){sum ->
            binding.tvAllCategoriesValueAverage.text = sum
        }
    }

    private fun observeAverageStatAllCategoriesAllTime() {
        viewModel.durationByAllCategoriesAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvAllCategoriesValueAverage.text = sum
        }
    }

    private fun observeAverageStatAllCategoriesThisMonth() {
        viewModel.durationByAllCategoriesAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvAllCategoriesValueAverage.text = sum
        }
    }

    private fun observeAverageStatSelectedCategoriesThisMonth() {
        viewModel.durationZayAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvZaycevValueAverage.text = sum
        }

        viewModel.durationMyAppAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvMyAppValueAverage.text = sum
        }

        viewModel.durationAnkiAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvAnkiValueAverage.text = sum
        }

        viewModel.durationSkillboxAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvSkillboxValueAverage.text = sum
        }

        viewModel.durationPuzzleAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvPuzzleValueAverage.text = sum
        }

        viewModel.durationCommonEducationAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvCommonEducationValueAverage.text = sum
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
            binding.tvResultsTitle.text = "Results of the day"
        } else {
            binding.buttonDay.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isMonthButtonClicked) {
            binding.buttonMonth.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Results of the month"
        } else {
            binding.buttonMonth.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isYearButtonClicked) {
            binding.buttonYear.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Results of the year"
        } else {
            binding.buttonYear.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isAllButtonClicked) {
            binding.buttonAll.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Results of all time"
        } else {
            binding.buttonAll.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }
    }

    private fun observeAverageStatSelectedCategoriesAllTime(){
        viewModel.durationZayAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvZaycevValueAverage.text = sum
        }

        viewModel.durationMyAppAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvMyAppValueAverage.text = sum
        }

        viewModel.durationAnkiAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvAnkiValueAverage.text = sum
        }

        viewModel.durationSkillboxAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvSkillboxValueAverage.text = sum
        }

        viewModel.durationPuzzleAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvPuzzleValueAverage.text = sum
        }

        viewModel.durationCommonEducationAvByAllTime.observe(viewLifecycleOwner) { sum ->
            binding.tvCommonEducationValueAverage.text = sum
        }
    }

    private fun observeAverageStatSelectedCategoriesThisYear(){
        viewModel.durationZayAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvZaycevValueAverage.text = sum
        }

        viewModel.durationMyAppAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvMyAppValueAverage.text = sum
        }

        viewModel.durationAnkiAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvAnkiValueAverage.text = sum
        }

        viewModel.durationSkillboxAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvSkillboxValueAverage.text = sum
        }

        viewModel.durationPuzzleAvByYear.observe(viewLifecycleOwner) { sum ->
            binding.tvPuzzleValueAverage.text = sum
        }

        viewModel.durationCommonEducationAvByMonth.observe(viewLifecycleOwner) { sum ->
            binding.tvCommonEducationValueAverage.text = sum
        }
    }
}