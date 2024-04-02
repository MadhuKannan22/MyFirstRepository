package com.example.databindingtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingtask.databinding.CardViewDesignBinding


class MyViewHolder(private val deleteCallback: (RegisterEntity) -> Unit) :
    ListAdapter<RegisterEntity, MyViewHolder.UserViewHolder>(UserComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            CardViewDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)

    }

    inner class UserViewHolder(private val binding: CardViewDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(register: RegisterEntity) {
            val holder = UserViewHolder(binding)
            binding.userId = register
            binding.password = register
            binding.decelerate.setOnClickListener {
                deleteCallback(register)
            }
            binding.executePendingBindings()
            /*binding.rowLayout.setOnClickListener {
                val action =
                    UserListFragmentDirections.actionUserListFragmentToUpdateFragment(register)
                holder.itemView.findNavController().navigate(action)

            }*/
        }
    }

    class UserComparator : DiffUtil.ItemCallback<RegisterEntity>() {
        override fun areItemsTheSame(oldItem: RegisterEntity, newItem: RegisterEntity): Boolean {
            return oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: RegisterEntity, newItem: RegisterEntity): Boolean {
            return oldItem == newItem
        }
    }
}
