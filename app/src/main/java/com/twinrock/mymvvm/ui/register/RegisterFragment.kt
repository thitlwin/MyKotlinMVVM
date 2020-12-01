package com.twinrock.mymvvm.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.twinrock.mymvvm.R
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.databinding.RegisterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment(), View.OnClickListener {

    private val viewModel: RegisterViewModel by viewModels()

    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        binding.buttonUserList.setOnClickListener(this)
        binding.buttonRegister.setOnClickListener(this)
        return binding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.buttonRegister -> {
                registerUser()
                                   }
            R.id.buttonUserList -> {
                val direction = RegisterFragmentDirections.actionRegisterFragmentToUserListFragment()
                v.findNavController().navigate(direction)
            }
        }
    }

    private fun registerUser() {
        if (formValidation()) {
            val user = User(id = null,
                name = binding.editTextName.text.toString(),
                email = binding.editTextEmail.text.toString(),
                password = binding.editTextPassword.text.toString())
            viewModel.registerUser(user)
        }
    }

    private fun formValidation(): Boolean {
        if (TextUtils.isEmpty(binding.editTextName.text)) {
            binding.editTextName.setError("Name is required")
            return false
        }
        if (TextUtils.isEmpty(binding.editTextEmail.text)) {
            binding.editTextEmail.setError("Email is required")
            return false
        }

        if (TextUtils.isEmpty(binding.editTextPassword.text)) {
            binding.editTextPassword.setError("Password is required")
            return false
        }
        return true
    }
}