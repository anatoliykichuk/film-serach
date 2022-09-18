package ru.geekbrains.filmserach.model.dto

import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("_id") var Id : String? = null,
    @SerializedName("url") var url : String? = null,
    @SerializedName("previewUrl") var previewUrl : String? = null
)
