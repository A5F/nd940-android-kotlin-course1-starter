package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        //very simple layout xml, i used Linear Layout for better perfomance
        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instruction, container, false)

        binding.instructionTitle.text = getString(R.string.instruction_title)
        binding.instructionSubtitle.text = getString(R.string.instruction_subtitle)

        binding.btnGoForward.setOnClickListener {
            findNavController().navigate(R.id.action_instruction_to_list)
        }

        return binding.root
    }
}