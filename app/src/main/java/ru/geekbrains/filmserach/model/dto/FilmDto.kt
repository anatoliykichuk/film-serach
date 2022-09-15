package com.example.example

data class FilmDto (
  var id: Int? = null,
  var externalId: ExternalId? = ExternalId(),
  var logo: Logo? = Logo(),
  var poster: String? = null,
  var rating: Rating? = Rating(),
  var votes: Votes? = Votes(),
  var type: String? = null,
  var name: String? = null,
  var description: String? = null,
  var year: Int? = null,
  var alternativeName: String? = null,
  var enName: String? = null,
  var movieLength: String? = null,
  var names: ArrayList<Names> = arrayListOf(),
  var shortDescription: String? = null
)