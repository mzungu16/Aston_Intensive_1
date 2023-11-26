package com.example.aston_lesson_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_lesson_1.databinding.MusicItemRecyclerBinding

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    val listOfMusic = mutableListOf<Music>()

    inner class MusicViewHolder(binding: MusicItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val itemBinding =
            MusicItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(itemBinding)
    }

    override fun getItemCount() = listOfMusic.size

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        with(holder) {

        }
    }
}