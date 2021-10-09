package com.example.mystat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mystat.databinding.FragmentBrainBinding
import com.example.mystat.databinding.FragmentChessBinding
import com.example.mystat.databinding.FragmentEngBinding
import com.example.mystat.databinding.FragmentProgrammingBinding

class BrainFragment: Fragment(R.layout.fragment_brain) {

    val binding: FragmentBrainBinding by viewBinding(FragmentBrainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}