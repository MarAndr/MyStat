package com.mystat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mystat.databinding.FragmentEngBinding

class EnglishFragment: Fragment(R.layout.fragment_eng) {

    val binding: FragmentEngBinding by viewBinding(FragmentEngBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}