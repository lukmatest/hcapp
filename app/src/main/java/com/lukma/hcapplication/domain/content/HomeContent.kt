package com.lukma.hcapplication.domain.content

data class HomeContent(
    val productSection: ProductSection,
    val articleSection: ArticleSection
) {
    data class ProductSection(val items: List<Product>)

    data class ArticleSection(val title: String, val items: List<Article>)
}
