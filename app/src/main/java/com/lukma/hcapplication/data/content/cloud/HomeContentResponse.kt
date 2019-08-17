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
        data class ProductSection(val items: List<ProductItem>?) : Section(SectionType.PRODUCTS)

        data class ArticleSection(
            @Json(name = "section_title")
            val title: String?,
            @Json(name = "items")
            val items: List<ArticleItem>?
        ) : Section(SectionType.ARTICLES)
    }
}
