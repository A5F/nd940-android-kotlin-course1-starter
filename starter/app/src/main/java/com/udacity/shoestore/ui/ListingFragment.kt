package com.udacity.shoestore.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentListingBinding
import com.udacity.shoestore.databinding.ItemShoeBinding

class ListingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentListingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_listing, container, false)

        binding.fab.setOnClickListener {
            //SCHERMATA ADD
            findNavController().navigate(R.id.action_list_to_detail)
        }

        val viewModel = (activity as MainActivity).getMainViewModel()

        viewModel.shoeEventLiveData.observe(requireActivity(), Observer { shoeList ->
            if (isAdded) {
                binding.llList.removeAllViews()

                shoeList?.forEach { item ->
                    val bindingItem: ItemShoeBinding =
                        DataBindingUtil.inflate(inflater, R.layout.item_shoe, binding.llList, false)
                    bindingItem.model = item
                    bindingItem.root.setOnClickListener {
                        Log.d("home", "click-> ${item.name}")
                        viewModel.modifyShoeEnabled = true
                        val direction = ListingFragmentDirections.actionListToDetail(item)
                        findNavController().navigate(direction)
                    }
                    binding.llList.addView(bindingItem.root)
                }
            }
        })

        viewModel.getShoeList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
                Log.d("home", "click-> back")
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
        (requireActivity() as MainActivity).hideNavigationArrow()
        setHasOptionsMenu(true)
    }


}