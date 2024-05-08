package com.example.travelai.domain.home

data class DiscoverCity(
    val img : Int, //string with API
    val category : String,
    val title :String ,
    val rating : Double,
    val isLiked : Boolean = false
)