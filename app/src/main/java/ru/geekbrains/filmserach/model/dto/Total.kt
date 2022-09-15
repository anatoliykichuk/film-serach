package com.example.example

data class Total (
  var films: ArrayList<FilmDto> = arrayListOf(),
  var total: Int? = null,
  var limit: Int? = null,
  var page: Int? = null,
  var pages: Int? = null
)