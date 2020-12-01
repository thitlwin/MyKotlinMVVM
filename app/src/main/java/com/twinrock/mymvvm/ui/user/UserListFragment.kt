package com.twinrock.mymvvm.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.twinrock.mymvvm.R
import com.twinrock.mymvvm.databinding.FragmentUserListBinding
import com.twinrock.mymvvm.databinding.RegisterFragmentBinding
import com.twinrock.mymvvm.ui.user.dummy.DummyContent

/**
 * A fragment representing a list of Items.
 */
class UserListFragment : Fragment() {

    private var columnCount = 1

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        // Set the adapter
        with(binding.recyclerUserList) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = UserRecyclerViewAdapter(DummyContent.ITEMS)
        }
        binding.toolbar.setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
        return binding.root
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            UserListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}