package com.example.example

import com.google.gson.annotations.SerializedName

data class Logo (

  @SerializedName("_id" ) var Id  : String? = null,
  @SerializedName("url" ) var url : String? = null

)