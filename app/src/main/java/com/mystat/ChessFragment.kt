package com.mystat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mystat.databinding.FragmentChessBinding

class ChessFragment: Fragment(R.layout.fragment_chess) {

    val binding: FragmentChessBinding by viewBinding(FragmentChessBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}