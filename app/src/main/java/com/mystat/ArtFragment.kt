package com.mystat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mystat.databinding.*

class ArtFragment: Fragment(R.layout.fragment_art) {

    val binding: FragmentArtBinding by viewBinding(FragmentArtBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }
}