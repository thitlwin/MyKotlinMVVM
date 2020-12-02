package com.twinrock.mymvvm.ui.user_edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.twinrock.mymvvm.R
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.databinding.FragmentUserEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserEditFragment : Fragment() {

    private val viewModel: UserEditViewModel by viewModels()

    private var _binding: FragmentUserEditBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = UserEditFragment()
    }

    val args: UserEditFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = args.user
        val userId = args.userId
        binding.textViewUserId.setText("#$userId")
        binding.editTextName.setText(user.name)
        binding.editTextEmail.setText(user.email)
        binding.editTextPassword.setText(user.password)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserEditBinding.inflate(inflater, container, false)
        binding.buttonRegister.setOnClickListener { updateUser()}
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        return binding.root
    }


    private fun updateUser() {
        if (formValidation()) {
            val user = User(id = args.userId,
                name = binding.editTextName.text.toString(),
                email = binding.editTextEmail.text.toString(),
                password = binding.editTextPassword.text.toString())
            viewModel.updateUser(user)
            Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
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