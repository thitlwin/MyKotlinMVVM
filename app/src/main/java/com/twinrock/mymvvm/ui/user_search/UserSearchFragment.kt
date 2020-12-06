package com.twinrock.mymvvm.ui.user_search

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.databinding.FragmentUserSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserSearchFragment : Fragment() {

    val TAG =javaClass.name
    val viewModel: UserSearchViewModel by viewModels()

    private var _binding: FragmentUserSearchBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserSearchBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.buttonSearch.setOnClickListener { onSearchButtonClick() }
        binding.cardViewUserInfo.visibility = View.GONE
        observeProgressLoading()
        observeUserExistOrNot()
        observeUserFromSearchResult()
        return binding.root
    }

    private fun observeUserFromSearchResult() {
        viewModel.user.observe(viewLifecycleOwner, {
            bindUserToView(it)
        })
    }

    private fun observeUserExistOrNot() {
        viewModel.isUserExist.observe(viewLifecycleOwner, Observer {
            if (!it)
                displayDialog("Not Found")
        })
    }

    private fun displayDialog(message: String?) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Close") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun observeProgressLoading() {
        viewModel.displayLoading.observe(viewLifecycleOwner, Observer {
            if (it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        })
    }

    private fun onSearchButtonClick() {
        binding.cardViewUserInfo.visibility = View.GONE
        if (frmValidation()) {
            if (binding.rdoEmail.isChecked)
                searchByUserEmail()
            else if (binding.rdoName.isChecked)
                searchByUserName()
            else
                searchByUserId()
        }
    }

    private fun searchByUserEmail() {
        Log.i(TAG, "search by email-----")
        var user = viewModel.searchUserByEmail(binding.editTextSearch.text.toString())
    }

    private fun searchByUserName() {
        Log.i(TAG, "search by name-----")
        viewLifecycleOwner.lifecycleScope.launch {
            val res = viewModel.searchUserByName(binding.editTextSearch.text.toString())
            if (res != null)
                bindUserToView(res)
            else
                displayDialog("Not found!")
        }
    }

    private fun searchByUserId() {
        Log.i(TAG, "search by id-----")
        if (!binding.editTextSearch.text!!.isDigitsOnly()) {
            binding.textInputLayoutSearch.error = "Invalid id."
            return
        }
        // call the LiveData & observe value
        viewModel.searchUserById(binding.editTextSearch.text.toString().toInt())
            .observe(viewLifecycleOwner, {
                if (it != null) {
                    try {
                        bindUserToView(it)
                    }catch (e: Exception) {
                        displayDialog(e.message)
                    }
                } else
                    displayDialog("Not found!")
            })
    }

    private fun bindUserToView(user: User) {
        binding.cardViewUserInfo.visibility = View.VISIBLE
        binding.textViewUserId.text = user.id.toString()
        binding.textViewUserEmail.text = user.email.toString()
        binding.textViewUserName.text = user.name.toString()
    }

    private fun frmValidation(): Boolean {
        if (TextUtils.isEmpty(binding.editTextSearch.text)){
            binding.textInputLayoutSearch.error = "Please enter value here"
            return false
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}