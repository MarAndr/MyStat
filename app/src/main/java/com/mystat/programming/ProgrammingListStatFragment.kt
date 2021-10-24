package com.mystat.programming

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mystat.R
import com.mystat.databinding.FragmentProgrammingStatListBinding
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class ProgrammingListStatFragment: Fragment(R.layout.fragment_programming_stat_list) {

    private val binding: FragmentProgrammingStatListBinding by viewBinding(FragmentProgrammingStatListBinding::bind)
    private val viewModel: ProgrammingViewModel by viewModels()
    private var statListAdapter: StatListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        viewModel.getProgrammingStat()
        observeList()
        binding.list.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener{
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                Timber.d("item click")
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })
    }


    private fun initList(){
        statListAdapter = StatListAdapter({id ->
            val action = ProgrammingListStatFragmentDirections.actionProgrammingListStatFragmentToUpdateStatFragment(id)
            findNavController().navigate(action)
        }, {
            viewModel.deleteStatElement(it)
            Snackbar.make(requireView(), "deleted", Snackbar.LENGTH_SHORT).show()
        }
        )
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