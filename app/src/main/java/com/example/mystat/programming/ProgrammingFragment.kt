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
        viewModel.getDurationSumForAllCategoriesToday()
        viewModel.getDurationSumForAllCategoriesAllTime()

        viewModel.apply {
            ProgrammingTypes.values().forEach {
                viewModel.getDurationSumByTypesAllTime(it)
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
            viewModel.addProgrammingStat(programmingStat)
            viewModel.getDurationSumForAllCategoriesAllTime()
            ProgrammingTypes.values().forEach {
                viewModel.getDurationSumByTypesAllTime(it)
            }
        }

        //Total sum buttons click ---------------------------------------------

        binding.buttonDay.setOnClickListener {
            changeButtonColor(ButtonTypes.DAY)
            ProgrammingTypes.values().forEach {
                viewModel.getDurationSumByTypesToday(it)
            }
            viewModel.getDurationSumForAllCategoriesToday()
        }

        binding.buttonMonth.setOnClickListener {
            changeButtonColor(ButtonTypes.MONTH)
            viewModel.getDurationSumForAllCategoriesThisMonth()
            ProgrammingTypes.values().forEach {
                viewModel.getDurationSumByTypesThisMonth(it)
            }
        }

        binding.buttonYear.setOnClickListener {
            changeButtonColor(ButtonTypes.YEAR)
            viewModel.getDurationSumForAllCategoriesThisYear()
            ProgrammingTypes.values().forEach {
            viewModel.getDurationSumByTypesThisYear(it)
            }
        }

        binding.buttonAll.setOnClickListener {
            changeButtonColor(ButtonTypes.ALL)
            ProgrammingTypes.values().forEach {
            viewModel.getDurationSumByTypesAllTime(it)
            }
            viewModel.getDurationSumForAllCategoriesAllTime()
        }

        //Average buttons click -----------------------------------------

        binding.buttonAvMonth.setOnClickListener {
            changeButtonColor(ButtonTypes.AV_MONTH)
        }

        binding.buttonAvYear.setOnClickListener {
            changeButtonColor(ButtonTypes.AV_YEAR)
            viewModel.apply {
                getProgrammingAvDayStatByYearAllCategories()
                ProgrammingTypes.values().forEach {
                getProgrammingAvDayStatByYearByType(it)
                }
            }
        }

        binding.buttonAvAll.setOnClickListener {
            changeButtonColor(ButtonTypes.AV_ALL)
        }

        //Observing --------------------------------------------------------

        observe()
    }

    private fun observe() {

        //duration sum for all time all categories

        viewModel.durationSumAllCategoriesAllTime.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvAllValue.text = sum.toString()
            } else {
                binding.tvAllValue.text = "0"
            }
        }

        //duration sum for all time for selected categories

       viewModel.programmingStatSumZay.observe(viewLifecycleOwner) { sum ->
           if (sum != null){
            binding.tvZaycevValue.text = sum.toString()
           } else {
            binding.tvZaycevValue.text = "0"
           }
        }

        viewModel.programmingStatSumSkillbox.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
            binding.tvSkillboxValue.text = sum.toString()
            } else {
                binding.tvSkillboxValue.text = "0"
            }
        }

        viewModel.programmingStatSumAnki.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
            binding.tvAnkiValue.text = sum.toString()
            } else {
            binding.tvAnkiValue.text = "0"
            }
        }

        viewModel.programmingStatSumMyApp.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
            binding.tvMyAppValue.text = sum.toString()
            } else {
            binding.tvMyAppValue.text = "0"
            }
        }

        viewModel.programmingStatSumCommonEducation.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
            binding.tvCommonEducationValue.text = sum.toString()
            } else {
            binding.tvCommonEducationValue.text = "0"
            }
        }

        viewModel.programmingStatSumPuzzle.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
            binding.tvPuzzleValue.text = sum.toString()
            } else {
            binding.tvPuzzleValue.text = "0"
            }
        }



        //duration sum for current day ------------------------------------------------------------

        //duration sum for current day all categories

        viewModel.durationSumAllCategoriesToday.observe(viewLifecycleOwner){ sum ->
            if (sum != null){
                binding.tvAllValue.text = sum.toString()
            } else{
                binding.tvAllValue.text = "0"
            }
        }

        //duration sum for current day for selected categories

        viewModel.programmingStatSumTodayZay.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvZaycevValue.text = sum.toString()
            } else {
                binding.tvZaycevValue.text = "0"
            }

        }

        viewModel.programmingStatSumTodaySkillbox.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvSkillboxValue.text = sum.toString()
            } else{
                binding.tvSkillboxValue.text = "0"
            }

        }

        viewModel.programmingStatSumTodayAnki.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
            binding.tvAnkiValue.text = sum.toString()
            } else{
            binding.tvAnkiValue.text = "0"
            }
        }

        viewModel.programmingStatSumTodayMyApp.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
            binding.tvMyAppValue.text = sum.toString()
            } else{
            binding.tvMyAppValue.text = "0"
            }
        }

        viewModel.programmingStatSumTodayCommonEducation.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
            binding.tvCommonEducationValue.text = sum.toString()
            } else{

            binding.tvCommonEducationValue.text = "0"
            }
        }

        viewModel.programmingStatSumTodayPuzzle.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
            binding.tvPuzzleValue.text = sum.toString()
            } else{
            binding.tvPuzzleValue.text = "0"
            }
        }

        //duration sum for this year ---------------------------------------------------------------

        //all categories

        viewModel.durationSumAllCategoriesThisYear.observe(viewLifecycleOwner){ sum ->
            if (sum != null){
                binding.tvAllValue.text = sum.toString()
            } else{
                binding.tvAllValue.text = "0"
            }
        }

        //selected categories

        viewModel.durationSumThisYearZay.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvZaycevValue.text = sum.toString()
            } else{
                binding.tvZaycevValue.text = "0"
            }
        }

        viewModel.durationSumThisYearMyApp.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvMyAppValue.text = sum.toString()
            } else{
                binding.tvMyAppValue.text = "0"
            }
        }

        viewModel.durationSumThisYearAnki.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvAnkiValue.text = sum.toString()
            } else{
                binding.tvAnkiValue.text = "0"
            }
        }

        viewModel.durationSumThisYearSkillbox.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvSkillboxValue.text = sum.toString()
            } else{
                binding.tvSkillboxValue.text = "0"
            }
        }

        viewModel.durationSumThisYearPuzzle.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvPuzzleValue.text = sum.toString()
            } else{
                binding.tvPuzzleValue.text = "0"
            }
        }

        viewModel.durationSumThisYearCommonEd.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvCommonEducationValue.text = sum.toString()
            } else{
                binding.tvCommonEducationValue.text = "0"
            }
        }

        //duration sum for this month ---------------------------------------------------------------

        //all categories

        viewModel.durationSumAllCategoriesThisMonth.observe(viewLifecycleOwner){ sum ->
            if (sum != null){
                binding.tvAllValue.text = sum.toString()
            } else{
                binding.tvAllValue.text = "0"
            }
        }

        //selected categories

        viewModel.durationSumThisMonthZay.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvZaycevValue.text = sum.toString()
            } else{
                binding.tvZaycevValue.text = "0"
            }
        }

        viewModel.durationSumThisMonthMyApp.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvMyAppValue.text = sum.toString()
            } else{
                binding.tvMyAppValue.text = "0"
            }
        }

        viewModel.durationSumThisMonthAnki.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvAnkiValue.text = sum.toString()
            } else{
                binding.tvAnkiValue.text = "0"
            }
        }

        viewModel.durationSumThisMonthSkillbox.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvSkillboxValue.text = sum.toString()
            } else{
                binding.tvSkillboxValue.text = "0"
            }
        }

        viewModel.durationSumThisMonthPuzzle.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvPuzzleValue.text = sum.toString()
            } else{
                binding.tvPuzzleValue.text = "0"
            }
        }

        viewModel.durationSumThisMonthCommonEd.observe(viewLifecycleOwner) { sum ->
            if (sum != null){
                binding.tvCommonEducationValue.text = sum.toString()
            } else{
                binding.tvCommonEducationValue.text = "0"
            }
        }

        //Average stat -----------------------------------------------------------------------------

        viewModel.programmingAvDayStatByYearAllCategories.observe(viewLifecycleOwner){ sum ->
            binding.tvAllValue.text = sum
        }

        viewModel.programmingAvDayStatByYearZaycevType.observe(viewLifecycleOwner){ sum ->
            binding.tvZaycevValue.text = sum
        }
    }

    private fun changeButtonColor(types: ButtonTypes){
        when(types){
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
        if (isDayButtonClicked){
            binding.buttonDay.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Results of the day"
        } else {
            binding.buttonDay.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isMonthButtonClicked){
            binding.buttonMonth.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Results of the month"
        } else {
            binding.buttonMonth.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isYearButtonClicked){
            binding.buttonYear.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Results of the year"
        } else {
            binding.buttonYear.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isAllButtonClicked){
            binding.buttonAll.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Results of all time"
        } else {
            binding.buttonAll.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isAvMonthButtonClicked){
            binding.buttonAvMonth.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Average day result by month"
        } else {
            binding.buttonAvMonth.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isAvYearButtonClicked){
            binding.buttonAvYear.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Average day result by year"
        } else {
            binding.buttonAvYear.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }

        if (isAvAllButtonClicked){
            binding.buttonAvAll.setBackgroundColor(requireContext().getColor(R.color.purple_500))
            binding.tvResultsTitle.text = "Average day result by all time"
        } else {
            binding.buttonAvAll.setBackgroundColor(requireContext().getColor(R.color.grey_200))
        }
    }
}