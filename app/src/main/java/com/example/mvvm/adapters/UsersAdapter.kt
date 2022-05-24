package com.example.mvvm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.UserListItemBinding
import com.example.mvvm.retrofitResponses.User

class UsersAdapter(var users: List<User>, var context: Context):RecyclerView.Adapter<UsersAdapter.UserRecyHolder>() {
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecyHolder {
        var inflater= LayoutInflater.from(parent.context)
        var recyListItemBinding= UserListItemBinding.inflate(inflater,parent,false)
        return UserRecyHolder(recyListItemBinding)
    }

    override fun getItemCount(): Int {
     return users.size
    }

    class UserRecyHolder(itemView: UserListItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        var itemBinding=itemView
    }

    override fun onBindViewHolder(holder: UserRecyHolder, position: Int) {
        var item=users.get(position)
        holder.itemBinding.user=item
        holder.itemBinding.executePendingBindings()
    }
}
