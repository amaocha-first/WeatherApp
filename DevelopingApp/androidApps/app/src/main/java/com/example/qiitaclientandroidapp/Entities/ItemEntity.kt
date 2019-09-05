package com.example.qiitaclientandroidapp.Entities

import com.squareup.moshi.Json

data class ItemEntity(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "body")
    val body: String
)