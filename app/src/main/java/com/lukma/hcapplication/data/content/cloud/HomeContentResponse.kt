package com.lukma.hcapplication.data.content.cloud

import com.squareup.moshi.Json

data class HomeContentResponse(
    val data: List<Content>?
) {
    enum class Section(val value: String) {
        PRODUCTS("products"),
        ARTICLES("articles")
    }

    sealed class Content(val section: Section?) {
        data class ProductContent(val items: List<Product>?) : Content(Section.PRODUCTS)
        data class ArticleContent(val items: List<Article>?) : Content(Section.ARTICLES)
    }

    data class Product(
        @Json(name = "product_name")
        val name: String?,
        @Json(name = "product_image")
        val image: String?,
        @Json(name = "link")
        val link: String?
    )

    data class Article(
        @Json(name = "article_title")
        val title: String?,
        @Json(name = "article_image")
        val image: String?,
        @Json(name = "link")
        val link: String?
    )
}
