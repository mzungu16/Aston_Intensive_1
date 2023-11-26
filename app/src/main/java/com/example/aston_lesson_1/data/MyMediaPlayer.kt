package com.example.aston_lesson_1.data

import android.media.MediaPlayer

object MyMediaPlayer {
    private var instance: MediaPlayer? = null

    fun getInstance(): MediaPlayer? {
        if (instance == null) {
            instance = MediaPlayer()
        }
        return instance
    }

    var currentIndex = -1
}