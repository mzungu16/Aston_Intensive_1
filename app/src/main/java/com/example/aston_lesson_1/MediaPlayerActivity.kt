package com.example.aston_lesson_1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_lesson_1.databinding.ActivityMediaPlayerBinding

class MediaPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMediaPlayerBinding
    private var musicList:ArrayList<Music>? = null
    private lateinit var currentMusic: Music
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val passedIntent = intent.extras
        val bundle: Bundle? = passedIntent?.getBundle("bundle")
        musicList = bundle?.getParcelableArrayList("LIST")
        setMusicResources()
    }

    private fun setMusicResources() {
        binding.run {
            currentMusic = musicList!![MyMediaPlayer.currentIndex]
            songTitle.text = currentMusic.title
            pausePlay.setOnClickListener{
                pausePlayClick()
            }
            next.setOnClickListener{

            }
            previous.setOnClickListener{

            }
        }
    }

    private fun pausePlayClick() {
        TODO("Not yet implemented")
    }
}