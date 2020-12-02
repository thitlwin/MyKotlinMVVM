package com.twinrock.mymvvm.ui.user

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twinrock.mymvvm.data.model.User
import com.twinrock.mymvvm.databinding.UserListItemBinding

class UserRecyclerViewAdapter(private val userItemClickListener: UserItemClickListener
) : RecyclerView.Adapter<UserRecyclerViewAdapter.UserListViewHolder>() {

    interface UserItemClickListener {
        fun onClickedUser(user: User)
    }

    private val items = ArrayList<User>()

    fun setItems(items: ArrayList<User>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
            UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ,userItemClickListener)
    }

    override fun onBindViewHolder(holderUserList: UserListViewHolder, position: Int) {
        val item = items[position]
        holderUserList.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class UserListViewHolder(private val binding: UserListItemBinding,
                                   private val itemClickListener: UserRecyclerViewAdapter.UserItemClickListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        lateinit var user: User
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: User) {
            user = item
            binding.userId.text = user.id.toString()
            binding.userName.text = user.name
            binding.userEmail.text = user.email
        }

        override fun onClick(p0: View?) {
            itemClickListener.onClickedUser(user)
        }
    }
}