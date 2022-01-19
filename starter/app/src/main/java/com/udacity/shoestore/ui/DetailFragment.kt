package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe
import java.lang.Exception

class DetailFragment : Fragment() {

    var shoeItem : Shoe? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        if (arguments?.get("shoe")!=null) {
            shoeItem = requireArguments().get("shoe") as Shoe?
        }

        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false)

        if (shoeItem!=null){
            binding.model = shoeItem
            binding.tiName.isEnabled = false
        }
        val viewModel = (activity as MainActivity).getMainViewModel()

        binding.btnSave.setOnClickListener {
            var size = 0.0
            try {
                size = binding.etSize.text.toString().toDouble()
            }catch (e:Exception){
            }
            viewModel.saveNewItem(
                Shoe(name = binding.etName.text.toString(),
                    size = size,
                    company = binding.etCompany.text.toString(),
                    description = binding.etDescription.text.toString(),
                    images = arrayListOf("", "")
                )
            )
            viewModel.modifyShoeEnabled = false
            findNavController().popBackStack()
        }

        binding.btnCancel.setOnClickListener {
            viewModel.modifyShoeEnabled = false
            findNavController().popBackStack()
        }

        return binding.root
    }
}