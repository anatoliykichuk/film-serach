package com.example.example

import com.google.gson.annotations.SerializedName
import ru.geekbrains.filmserach.data.net.dto.Poster

data class FilmDto (

    @SerializedName("externalId"       ) var externalId       : ExternalId?          = ExternalId(),
    @SerializedName("id"               ) var id               : Int?                 = null,
    @SerializedName("name"             ) var name             : String?              = null,
    @SerializedName("alternativeName"  ) var alternativeName  : String?              = null,
    @SerializedName("enName"           ) var enName           : String?              = null,
    @SerializedName("names"            ) var names            : ArrayList<Names>     = arrayListOf(),
    @SerializedName("genres"           ) var genres           : ArrayList<Genres>    = arrayListOf(),
    @SerializedName("countries"        ) var countries        : ArrayList<Countries> = arrayListOf(),
    @SerializedName("logo"             ) var logo             : Logo?                = Logo(),
    @SerializedName("poster"           ) var poster           : Poster?              = null,
    @SerializedName("rating"           ) var rating           : Rating?              = Rating(),
    @SerializedName("votes"            ) var votes            : Votes?               = Votes(),
    @SerializedName("budget"           ) var budget           : Budget?              = Budget(),
    @SerializedName("type"             ) var type             : String?              = null,
    @SerializedName("year"             ) var year             : Int?                 = null,
    @SerializedName("movieLength"      ) var movieLength      : String?              = null,
    @SerializedName("shortDescription" ) var shortDescription : String?              = null,
    @SerializedName("description"      ) var description      : String?              = null

)