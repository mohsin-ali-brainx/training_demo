package com.brainx.koindemoproject.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.brainx.koindemoproject.models.User

class UsersDiffUtils(
    private val oldList:List<User>,
    private val newList:List<User>
):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].id==newList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].equals(newList[newItemPosition])
    }
}
