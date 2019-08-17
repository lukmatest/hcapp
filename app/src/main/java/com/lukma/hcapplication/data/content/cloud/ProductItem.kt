package com.lukma.hcapplication.data.content.cloud

import com.squareup.moshi.Json

data class ProductItem(
    @Json(name = "product_name")
    val name: String?,
    @Json(name = "product_image")
    val image: String?,
    @Json(name = "link")
    val link: String?
)
