package com.example.mystat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mystat.databinding.*

class MeditationFragment: Fragment(R.layout.fragment_meditation) {

    val binding: FragmentMeditationBinding by viewBinding(FragmentMeditationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}