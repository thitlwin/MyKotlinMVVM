package com.twinrock.mymvvm.ui.user_register

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
import com.twinrock.mymvvm.databinding.FragmentUserRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRegisterFragment : Fragment(), View.OnClickListener {

    val TAG = javaClass.name
    private val viewModelUser: UserRegisterViewModel by viewModels()

    private var _binding: FragmentUserRegisterBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserRegisterBinding.inflate(inflater, container, false)
        binding.buttonUserList.setOnClickListener(this)
        binding.buttonRegister.setOnClickListener(this)
        binding.buttonSearchUser.setOnClickListener(this)

        subscribeUI()
        return binding.root
    }

    private fun subscribeUI() {
        viewModelUser.userList.observe(viewLifecycleOwner) { result ->
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
                val direction = UserRegisterFragmentDirections.actionRegisterFragmentToUserListFragment()
                v.findNavController().navigate(direction)
            }
            R.id.buttonSearchUser -> {
                v.findNavController().navigate(UserRegisterFragmentDirections.actionRegisterFragmentToUserSearchFragment())
            }
        }
    }

    private fun registerUser() {
        if (formValidation()) {
            val user = User(id = null,
                name = binding.editTextName.text.toString(),
                email = binding.editTextEmail.text.toString(),
                password = binding.editTextPassword.text.toString())
            viewModelUser.registerUser(user)
            Toast.makeText(requireContext(), "Save Success", Toast.LENGTH_SHORT).show()
            clearForm()
        }
    }

    private fun clearForm() {
        binding.editTextEmail.setText("")
        binding.editTextName.setText("")
        binding.editTextPassword.setText("")
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