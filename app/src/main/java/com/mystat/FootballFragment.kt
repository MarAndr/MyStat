package com.mystat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mystat.databinding.FragmentFootballBinding

class FootballFragment: Fragment(R.layout.fragment_football) {

    val binding: FragmentFootballBinding by viewBinding(FragmentFootballBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}