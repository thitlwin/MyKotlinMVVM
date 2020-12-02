package com.twinrock.mymvvm.ui.register

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.twinrock.mymvvm.R
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.databinding.RegisterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(), View.OnClickListener {

    val TAG = javaClass.name
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
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI() {
        viewModel.userList.observe(viewLifecycleOwner) { result ->
            binding.buttonUserList.setText("User List (${result.size})")
        }
    }

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
            Toast.makeText(requireContext(), "Save Success", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formValidation(): Boolean {
        var hasError = false
        if (TextUtils.isEmpty(binding.editTextName.text)) {
            binding.textInputLayoutUserName.error = "Name is required"
            hasError = true
        }
        if (TextUtils.isEmpty(binding.editTextEmail.text)) {
            binding.textInputLayoutEmail.error = "Email is required"
            hasError = true
        }

        if (TextUtils.isEmpty(binding.editTextPassword.text)) {
            binding.textInputLayoutPassword.error = "Password is required"
            hasError = true
        }
        if (hasError)
            return false
        return true
    }
}