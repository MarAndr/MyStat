package com.example.mystat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mystat.databinding.FragmentChessBinding
import com.example.mystat.databinding.FragmentEngBinding
import com.example.mystat.databinding.FragmentProgrammingBinding
import com.example.mystat.databinding.FragmentReadingBinding

class ReadingFragment: Fragment(R.layout.fragment_reading) {

    val binding: FragmentReadingBinding by viewBinding(FragmentReadingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}