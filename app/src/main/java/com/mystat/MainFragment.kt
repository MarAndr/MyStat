package com.mystat

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.mystat.databinding.FragmentMainBinding
import timber.log.Timber

class MainFragment: Fragment(R.layout.fragment_main) {

    val binding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    val viewModel: LoginViewModel by viewModels()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (auth.currentUser != null){
        viewModel.setUpActionBar(requireContext())
        }
        binding.apply {

            appCompatButtonProgramming.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment2_to_programmingFragment)
                Timber.d("currentUser = ${FirebaseAuth.getInstance().currentUser?.email}")
                Timber.d("currentUser = ${FirebaseAuth.getInstance().currentUser?.uid}")
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

            btnLogOut.setOnClickListener {
                auth.signOut()
                findNavController().navigate(R.id.action_mainFragment2_to_loggingFragment)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_signOut){
            auth.signOut()
            makeSnackBar("You logged out from your account!")
            findNavController().navigate(R.id.action_mainFragment2_to_loggingFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun makeSnackBar(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

}