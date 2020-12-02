package com.twinrock.mymvvm.ui.user

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.databinding.UserListItemBinding

class UserRecyclerViewAdapter(
    private val values: List<User>
) : RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                println("click card----")
            }
        }

        fun bind(item: User) {
            binding.apply {
                user = item
                executePendingBindings()
            }
        }
    }
}