package com.example.example

import com.google.gson.annotations.SerializedName

data class ExternalId (

  @SerializedName("_id"  ) var Id   : String? = null,
  @SerializedName("imdb" ) var imdb : String? = null

)