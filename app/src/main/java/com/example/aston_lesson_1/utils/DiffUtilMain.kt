package com.example.aston_lesson_1.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.aston_lesson_1.data.Music

class DiffUtilMain(private val oldList: List<Music>, private val newList: List<Music>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}