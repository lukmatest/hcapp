package com.lukma.hcapplication.data.content.cloud

import com.squareup.moshi.Json

data class HomeContentResponse(
    val data: List<Section>?
) {
    enum class SectionType(val value: String) {
        PRODUCTS("products"),
        ARTICLES("articles")
    }

    sealed class Section(val section: SectionType?) {
        data class ProductSection(val items: List<Product>?) : Section(SectionType.PRODUCTS)
        data class ArticleSection(
            @Json(name = "section_title")
            val title: String?,
            @Json(name = "items")
            val items: List<Article>?
        ) : Section(SectionType.ARTICLES)
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
