package com.example.demouserlist.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demouserlist.data.User
import com.example.demouserlist.databinding.ItemUserBinding

interface UserActionListener {
    fun selectUser(user: User)
}

class UsersRvAdapter(
    private val actionListener: UserActionListener,
) : RecyclerView.Adapter<UsersRvAdapter.UsersViewHolder>(), View.OnClickListener {

    var users: List<User> = emptyList()
        @SuppressLint("NotifyDataSetChanged") set(newValue) {
            val diffResult = DiffUtil.calculateDiff(UserDiffCallback(field, newValue))
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onClick(v: View) {
        val user = v.tag as User
        actionListener.selectUser(user)
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return UsersViewHolder(binding)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        holder.itemView.tag = user
        with(holder.binding) {
            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName
            tvPhoneNumber.text = user.phoneNumber
            ivUserImage.setImageResource(user.image)
        }
    }

    class UsersViewHolder(
        val binding: ItemUserBinding,
    ) : RecyclerView.ViewHolder(binding.root)
}

class UserDiffCallback(
    private val oldList: List<User>,
    private val newList: List<User>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}