package com.mystat.programming

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mystat.databinding.FragmentUpdateStatBinding
import android.widget.ArrayAdapter
import androidx.core.view.isVisible


class UpdateStatFragment : Fragment(com.mystat.R.layout.fragment_update_stat), AdapterView.OnItemSelectedListener {

    private val binding: FragmentUpdateStatBinding by viewBinding(FragmentUpdateStatBinding::bind)
    private val args: UpdateStatFragmentArgs by navArgs()
    private val viewModel: ProgrammingViewModel by viewModels()
    private var type: ProgrammingTypes = ProgrammingTypes.ZAYCEV

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.statId
        viewModel.getProgrammingStatById(id)
        val types = ProgrammingTypes.values().map { it.name }
        val typesAdapter = ArrayAdapter(
            requireContext(),
            com.mystat.R.layout.types_list_item,
            types
        )
        binding.spinnerTypeEdittedValue.adapter = typesAdapter
        binding.spinnerTypeEdittedValue.onItemSelectedListener = this
        observe()

        binding.apply {

            durationValue.setOnClickListener {
                durationEdittedValue.isVisible = true
                durationEditButton.isVisible = true
                val editedTextDuration = durationEdittedValue.text
                durationEditButton.setOnClickListener {
                    durationValue.text = editedTextDuration
                    durationEdittedValue.isVisible = false
                    durationEditButton.isVisible = false
                    viewModel.updateDurationStat(id, editedTextDuration.toString().toInt())
                }
            }
        }

        binding.apply {
            typeValue.setOnClickListener {
                typeEditedContainer.isVisible = true
                typeEditButton.setOnClickListener {
                    typeEditedContainer.isVisible = false
                    viewModel.updateTypeStat(id, type)
                }
            }
        }
    }

    private fun observe() {
        viewModel.programmingStatById.observe(viewLifecycleOwner) { programmingStat ->
            binding.durationValue.text = programmingStat.durationInMin.toString()
            binding.dateValue.text = programmingStat.time.toString()
            binding.typeValue.text = programmingStat.type.toString()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text = parent?.getItemAtPosition(position).toString()
        binding.typeValue.text = text
        type = ProgrammingTypes.valueOf(text)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}