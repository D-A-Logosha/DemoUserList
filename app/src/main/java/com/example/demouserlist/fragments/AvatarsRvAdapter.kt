package com.example.demouserlist.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demouserlist.databinding.ItemImageBinding

interface AvatarActionListener {
    fun selectAvatar(image: Int)
}

class AvatarsRvAdapter(
    private val actionListener: AvatarActionListener
) : RecyclerView.Adapter<AvatarsRvAdapter.UsersViewHolder>(), View.OnClickListener {

    var images: List<Int> = emptyList()
        @SuppressLint("NotifyDataSetChanged") set(newValue) {
            val diffResult = DiffUtil.calculateDiff(AvatarDiffCallback(field, newValue))
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onClick(v: View) {
        val image = v.tag as Int
        actionListener.selectAvatar(image)
    }

    override fun getItemCount(): Int = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return UsersViewHolder(binding)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val image = images[position]
        holder.itemView.tag = image
        with(holder.binding) {
            ivUserImage.setImageResource(image)
        }
    }

    class UsersViewHolder(
        val binding: ItemImageBinding,
    ) : RecyclerView.ViewHolder(binding.root)
}

class AvatarDiffCallback(
    private val oldList: List<Int>,
    private val newList: List<Int>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}