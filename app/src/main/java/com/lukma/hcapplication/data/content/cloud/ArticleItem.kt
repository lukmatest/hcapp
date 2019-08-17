package com.lukma.hcapplication.data.content.cloud

import com.squareup.moshi.Json

data class ArticleItem(
    @Json(name = "article_title")
    val title: String?,
    @Json(name = "article_image")
    val image: String?,
    @Json(name = "link")
    val link: String?
)
