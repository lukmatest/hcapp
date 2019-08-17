package com.lukma.hcapplication.domain.content

data class HomeContent(
    val productSection: ProductSection,
    val articleSection: ArticleSection
) {
    data class ProductSection(val products: List<Product>)

    data class ArticleSection(val sectionTitle: String, val articles: List<Article>)
}
