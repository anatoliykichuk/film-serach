package com.example.example

import com.google.gson.annotations.SerializedName

data class Rating (

  @SerializedName("_id"                ) var Id                 : String? = null,
  @SerializedName("kp"                 ) var kp                 : Double? = null,
  @SerializedName("imdb"               ) var imdb               : Double? = null,
  @SerializedName("filmCritics"        ) var filmCritics        : Int?    = null,
  @SerializedName("russianFilmCritics" ) var russianFilmCritics : Int?    = null,
  @SerializedName("await"              ) var await              : Int?    = null

)