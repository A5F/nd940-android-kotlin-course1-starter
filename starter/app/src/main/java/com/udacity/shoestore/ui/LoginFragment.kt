package com.udacity.shoestore.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        activity?.title = getString(R.string.app_name)
        binding.btnLoginNew.setOnClickListener {
            if (checkInputField(binding.etUsername.text.toString(), binding.etUsername.text.toString())) {
                findNavController().navigate(R.id.action_login_to_welcome)
            }else{
                Toast.makeText(requireContext(), "DATI NON CORRETTI", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnLoginOld.setOnClickListener {
            if (checkInputField(binding.etUsername.text.toString(), binding.etUsername.text.toString())) {
                val responseCheckUser = checkUser(binding.etUsername.text.toString(), binding.etUsername.text.toString())
                when (responseCheckUser) {
                    "OK" -> {
                        findNavController().navigate(R.id.action_login_to_welcome)
                    }
                    "NO_USER_FOUND" -> {
                        Toast.makeText(requireContext(), "UTENTE NON TROVATO", Toast.LENGTH_LONG)
                            .show()
                    }
                    "INCORRECT_PASSWORD" -> {
                        Toast.makeText(requireContext(), "PASSWORD NON CORRETTA", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "DATI NON CORRETTI", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    private fun checkInputField(username: String, password: String): Boolean{
        return username.isNotEmpty() && password.isNotEmpty()
    }

    private fun checkUser(username: String, password: String): String{
        val mockedUser= getMockedUser()
        if (mockedUser.get(username)?.isNotEmpty() == true){
            if (mockedUser.get(username).equals(password)){
                return "OK"
            }else{
                return "INCORRECT_PASSWORD"
            }
        }else{
            return "NO_USER_FOUND"
        }
    }

    private fun getMockedUser(): HashMap<String, String>{
        val users = hashMapOf<String, String>()
        users.put("admin", "admin")
        return users
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }
    val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Handle the back button event
            Log.d("login", "click-> back")
            activity?.finish()
        }
    }


}