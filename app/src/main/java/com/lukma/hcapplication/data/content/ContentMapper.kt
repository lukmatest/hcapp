package com.lukma.hcapplication.data.content

import com.lukma.hcapplication.data.content.cloud.HomeContentResponse
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.Product

fun transform(value: HomeContentResponse): Pair<List<Product>, List<Article>> {
    val products = value.data
        ?.find { it.section == HomeContentResponse.Section.PRODUCTS }
        ?.let { it as HomeContentResponse.Content.ProductContent }
        ?.items?.map(::transform)
        ?: throw NoSuchElementException("products")

    val articles = value.data
        .find { it.section == HomeContentResponse.Section.ARTICLES }
        ?.let { it as HomeContentResponse.Content.ArticleContent }
        ?.items?.map(::transform)
        ?: throw NoSuchElementException("articles")

    return Pair(products, articles)
}

fun transform(value: HomeContentResponse.Product) = Product(
    value.name ?: throw NoSuchElementException("name"),
    value.image ?: throw NoSuchElementException("image"),
    value.link ?: throw NoSuchElementException("link")
)

fun transform(value: HomeContentResponse.Article) = Article(
    value.title ?: throw NoSuchElementException("name"),
    value.image ?: throw NoSuchElementException("image"),
    value.link ?: throw NoSuchElementException("link")
)
