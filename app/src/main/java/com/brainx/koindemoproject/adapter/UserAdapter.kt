package com.brainx.koindemoproject.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brainx.koindemoproject.diffUtils.UsersDiffUtils
import com.brainx.koindemoproject.models.User
import com.brainx.koindemoproject.viewholder.UsersViewHolder

class UserAdapter:RecyclerView.Adapter<UsersViewHolder>() {
    private val userList: ArrayList<User> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder = UsersViewHolder.from(parent)

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) = holder.bind(position,userList[position])

    override fun getItemCount(): Int = userList.size

    private fun setDiffUtils(newData:List<User>){
        DiffUtil.calculateDiff(UsersDiffUtils(userList,newData)).dispatchUpdatesTo(this)
        userList.apply {
            clear()
            addAll(newData)
        }
    }

    fun setData(data:List<User>){
        setDiffUtils(data)
    }
}