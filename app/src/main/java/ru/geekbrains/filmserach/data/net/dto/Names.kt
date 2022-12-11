package com.example.example

import com.google.gson.annotations.SerializedName

data class Names (

  @SerializedName("_id"  ) var Id   : String? = null,
  @SerializedName("name" ) var name : String? = null

)