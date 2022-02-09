package com.brainx.koindemoproject.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.brainx.koindemoproject.R
import com.brainx.koindemoproject.databinding.UsersLayoutBinding
import com.brainx.koindemoproject.models.User

class UsersViewHolder(val itemBinding: UsersLayoutBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(
        mPosition: Int,
        mUser:User
    ) {
        itemBinding.apply {
            user = mUser
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): UsersViewHolder {
            return UsersViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.users_layout,
                    parent,
                    false
                )
            )
        }
    }
}