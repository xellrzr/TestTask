package com.example.testtask.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CatalogRequest(
    @SerializedName("region")
    val region: Region,
    @SerializedName("category")
    val category: List<Int>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int
) {
    data class Region(
        @SerializedName("levels")
        val levels: List<String?>,
        @SerializedName("lat")
        val lat: Double,
        @SerializedName("lng")
        val lng: Double
    )
}
