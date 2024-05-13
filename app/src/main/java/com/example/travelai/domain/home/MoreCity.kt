package com.example.travelai.domain.home

data class MoreCity(
    val name: String,
    val img: Int, //string for API,
    val isSpecial: Boolean = false,
    val navTo: Int? = null,
    val isBuildPlan:Boolean = false,
    var isSelected :Boolean = false
)
