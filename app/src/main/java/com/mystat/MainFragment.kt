package com.mystat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mystat.databinding.FragmentMainBinding
import timber.log.Timber

class MainFragment: Fragment(R.layout.fragment_main) {

    val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            appCompatButtonProgramming.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_programmingFragment)
                Timber.d("programming")
            }

            appCompatButtonEng.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_englishFragment)
                Timber.d("eng")
            }

            appCompatButtonChess.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_chessFragment)
                Timber.d("chess")
            }

            appCompatButtonReading.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_readingFragment)
                Timber.d("reading")
            }

            appCompatButtonMeditation.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_meditationFragment)
                Timber.d("meditation")
            }

            appCompatButtonArt.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_artFragment)
                Timber.d("art")
            }

            appCompatButtonSport.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_sportFragment)
                Timber.d("sport")
            }

            appCompatButtonFootball.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_footballFragment)
                Timber.d("football")
            }

            appCompatButtonVideo.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_videoFragment)
                Timber.d("video")
            }

            appCompatButtonBrain.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_brainFragment)
                Timber.d("brain")
            }
        }
    }

}