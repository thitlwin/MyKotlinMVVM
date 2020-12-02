package com.twinrock.mymvvm.ui.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.databinding.FragmentUserListBinding
import com.twinrock.mymvvm.ui.user_edit.UserEditFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment(), UserRecyclerViewAdapter.UserItemClickListener {

    val TAG = javaClass.name
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModels()

    private var userList: ArrayList<User> = ArrayList()
    lateinit var adapter: UserRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        subscribeUI()

        binding.toolbar.setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
        return binding.root
    }

    private fun setUpRecyclerView() {
        // Set the adapter
        adapter = UserRecyclerViewAdapter(this)
        binding.recyclerUserList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerUserList.adapter = adapter
    }

    private fun subscribeUI() {
        viewModel.userList.observe(viewLifecycleOwner, Observer<List<User>> {
            Log.i(TAG, "user size = ${it.size}")
            adapter.setItems(it as ArrayList<User>)
        })
    }

    override fun onClickedUser(user: User) {
        Log.i(TAG, "onClickedUser---userId=${user.id}, name=${user.name}")
        val direction = UserListFragmentDirections.actionUserListFragmentToUserEditFragment(user.id!!, user)
        findNavController().navigate(direction)
    }
}