package com.example.example

import com.google.gson.annotations.SerializedName

data class Budget (

  @SerializedName("_id"      ) var Id       : String? = null,
  @SerializedName("value"    ) var value    : Int?    = null,
  @SerializedName("currency" ) var currency : String? = null

)