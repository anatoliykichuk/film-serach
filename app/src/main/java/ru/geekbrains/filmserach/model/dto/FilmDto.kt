package com.example.example

import com.google.gson.annotations.SerializedName

data class FilmDto (

  @SerializedName("docs"  ) var docs  : ArrayList<Docs> = arrayListOf(),
  @SerializedName("total" ) var total : Int?            = null,
  @SerializedName("limit" ) var limit : Int?            = null,
  @SerializedName("page"  ) var page  : Int?            = null,
  @SerializedName("pages" ) var pages : Int?            = null

)