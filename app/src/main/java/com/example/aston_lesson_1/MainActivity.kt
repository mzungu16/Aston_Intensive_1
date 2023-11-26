package com.example.aston_lesson_1

import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aston_lesson_1.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listOfSongs = mutableListOf<Music>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        checkPermission(STORAGE_PERMISSION_API, STORAGE_PERMISSION_CODE)

        val projection = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DURATION
        )
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0"
        val cursor = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )
        fillListOfMusic(cursor)
        checkIsListNotEmpty()
    }

    private fun checkIsListNotEmpty() {
        if (listOfSongs.size == 0) {
            binding.musicPlaceHolder.visibility = View.VISIBLE
        } else {
            initRecycler()
        }
    }

    private fun fillListOfMusic(cursor: Cursor?) {
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val musicData = Music(cursor.getString(1), cursor.getString(0), cursor.getString(2))
                if (File(musicData.path).exists()) {
                    listOfSongs.add(musicData)
                }
            }
        }
    }

    private fun initRecycler() {
        binding.run {
            musicRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
//                adapter = MusicAdapter()
            }
        }
    }

    private fun checkPermission(str: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                str
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(str), requestCode)
        } else {
            Toast.makeText(this@MainActivity, "Permission already granted", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Storage Permission Granted", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@MainActivity, "Storage Permission Denied", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        private const val STORAGE_PERMISSION_CODE = 101

        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        private const val STORAGE_PERMISSION_API = android.Manifest.permission.READ_MEDIA_AUDIO
    }

}