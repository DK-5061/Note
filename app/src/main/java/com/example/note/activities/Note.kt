package com.example.note.activities

import com.google.gson.annotations.SerializedName

data class Note(
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String
)