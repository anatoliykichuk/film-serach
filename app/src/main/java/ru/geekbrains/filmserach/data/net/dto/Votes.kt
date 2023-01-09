package com.example.example

import com.google.gson.annotations.SerializedName

data class Votes (

  @SerializedName("_id"                ) var Id                 : String? = null,
  @SerializedName("kp"                 ) var kp                 : Int?    = null,
  @SerializedName("imdb"               ) var imdb               : Int?    = null,
  @SerializedName("filmCritics"        ) var filmCritics        : Int?    = null,
  @SerializedName("russianFilmCritics" ) var russianFilmCritics : Int?    = null,
  @SerializedName("await"              ) var await              : Int?    = null

)