package com.example.aston_lesson_1.ui


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.aston_lesson_1.data.Music
import com.example.aston_lesson_1.data.MyMediaPlayer
import com.example.aston_lesson_1.R
import com.example.aston_lesson_1.databinding.ActivityMediaPlayerBinding
import java.util.concurrent.TimeUnit


class MediaPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMediaPlayerBinding
    private lateinit var musicList: ArrayList<Music>
    private lateinit var currentMusic: Music
    private val mediaPlayer = MyMediaPlayer.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val passedIntent = intent.extras
        val bundle: Bundle? = passedIntent?.getBundle("bundle")
        musicList = bundle?.getParcelableArrayList("LIST")!!
        setMusicResources()
        this@MediaPlayerActivity.runOnUiThread(object : Runnable {
            override fun run() {
                if (mediaPlayer != null) {
                    binding.seekBar.progress = mediaPlayer.currentPosition
                    binding.currentTime.text = convertToMMSS(mediaPlayer.currentPosition.toString() + "")
                    if (mediaPlayer.isPlaying) {
                        binding.pausePlay.setImageResource(R.drawable.pause_button)
                    } else {
                        binding.pausePlay.setImageResource(R.drawable.play_button)
                    }
                }
                Handler(Looper.getMainLooper()).postDelayed(this,100)
            }
        })

        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }


    private fun setMusicResources() {
        binding.run {
            currentMusic = musicList[MyMediaPlayer.currentIndex]
            songTitle.text = currentMusic.title
            pausePlay.setOnClickListener {
                pauseSong()
            }
            next.setOnClickListener {
                playNextSong()
            }
            previous.setOnClickListener {
                playPreviousSong()
            }
            playSong()
        }
    }

    private fun pauseSong() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer.pause()
        } else {
            mediaPlayer?.start()
        }
    }

    private fun playPreviousSong() {
        if (MyMediaPlayer.currentIndex == 0) return
        MyMediaPlayer.currentIndex -= 1
        mediaPlayer?.reset()
        setMusicResources()
    }

    private fun playNextSong() {
        if (MyMediaPlayer.currentIndex == musicList.size - 1) return
        MyMediaPlayer.currentIndex += 1
        mediaPlayer?.reset()
        setMusicResources()
    }

    private fun playSong() {
        mediaPlayer?.reset()
        mediaPlayer?.setDataSource(currentMusic.path)
        mediaPlayer?.prepare()
        mediaPlayer?.start()
        binding.run {
            seekBar.apply {
                progress
                max = mediaPlayer?.duration!!
            }
        }

    }

    fun convertToMMSS(duration: String): String? {
        val millis = duration.toLong()
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1)
        )
    }

}