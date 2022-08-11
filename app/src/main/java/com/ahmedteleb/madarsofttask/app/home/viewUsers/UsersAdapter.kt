package com.ahmedteleb.madarsofttask.app.home.viewUsers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedteleb.madarsofttask.databinding.ItemUserBinding
import com.ahmedteleb.madarsofttask.domain.entity.UserEntity

class UsersAdapter : ListAdapter<UserEntity, UsersAdapter.ViewHolder>(UsersAdapterDiffCallback()) {

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            user: UserEntity,
            ) {

            binding.tvName.text = user.name
            binding.tvAge.text = user.age.toString()
            binding.tvJob.text = user.jobTitle
            binding.tvGender.text = user.gender.name
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))


}


class UsersAdapterDiffCallback : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.id == newItem.id
    }
}