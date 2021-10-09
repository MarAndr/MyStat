package com.example.mystat.programming

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mystat.R
import com.example.mystat.databinding.FragmentProgrammingBinding
import com.example.mystat.databinding.FragmentProgrammingStatListBinding
import com.example.mystat.utils.getProgrammingTypesFromRadioGroup
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime

class ProgrammingListStatFragment: Fragment(R.layout.fragment_programming_stat_list) {

    private val binding: FragmentProgrammingStatListBinding by viewBinding(FragmentProgrammingStatListBinding::bind)
    private val viewModel: ProgrammingViewModel by viewModels()
    private var statListAdapter: StatListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        viewModel.getProgrammingStat()
        observeList()
    }


    private fun initList(){
        statListAdapter = StatListAdapter()
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.list.apply {
            adapter = statListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(divider)
        }
    }

    private fun observeList(){
        viewModel.programmingStat.observe(viewLifecycleOwner){statList ->
            statListAdapter?.submitList(statList)
        }
    }
}