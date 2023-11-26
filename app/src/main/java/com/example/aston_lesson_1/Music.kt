package com.example.aston_lesson_1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(
    val path: String,
    val title: String,
    val duration: String,
) : Parcelable
