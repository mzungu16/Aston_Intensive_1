package com.example.aston_lesson_1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aston_lesson_1.data.Music
import com.example.aston_lesson_1.databinding.MusicItemRecyclerBinding
import com.example.aston_lesson_1.utils.DiffUtilMain

class MusicAdapter(private val onAdapterMusicListener: OnMusicClickListener) :
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    private var listOfMusic = mutableListOf<Music>()

    fun setListOfMusic(paramList: List<Music>) {
        val diffUtilCallback = DiffUtilMain(this.listOfMusic, paramList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        this.listOfMusic.clear()
        this.listOfMusic.addAll(paramList)
        diffResult.dispatchUpdatesTo(this@MusicAdapter)
    }

    inner class MusicViewHolder(
        binding: MusicItemRecyclerBinding,
        private var onMusicClickListener: OnMusicClickListener
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        val musicTitle = binding.musicTitleTxt
        override fun onClick(p0: View?) {
            onMusicClickListener.onMusicClick(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val itemBinding =
            MusicItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(itemBinding, onAdapterMusicListener)
    }

    override fun getItemCount() = listOfMusic.size

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        with(holder) {
            musicTitle.text = listOfMusic[position].title
        }
    }

    interface OnMusicClickListener {
        fun onMusicClick(position: Int)
    }

}