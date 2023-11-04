package com.example.testtask.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CatalogResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("result")
    val result: Int
)  {
    data class Data(
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("name")
        val name: String,
        @SerializedName("price")
        val price: Double,
        @SerializedName("seo_name")
        val seoName: String,
    )
}