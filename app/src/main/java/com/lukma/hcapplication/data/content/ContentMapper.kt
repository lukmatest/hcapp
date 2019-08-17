package com.lukma.hcapplication.data.content

import com.lukma.hcapplication.data.common.MissingPropertyException
import com.lukma.hcapplication.data.content.cloud.HomeContentResponse
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.Product

fun transform(value: HomeContentResponse): Pair<List<Product>, List<Article>> {
    val products = value.data
        ?.find { it.section == HomeContentResponse.Section.PRODUCTS }
        ?.let { it as HomeContentResponse.Content.ProductContent }
        ?.items?.map(::transform)
        ?: throw MissingPropertyException("products")

    val articles = value.data
        .find { it.section == HomeContentResponse.Section.ARTICLES }
        ?.let { it as HomeContentResponse.Content.ArticleContent }
        ?.items?.map(::transform)
        ?: throw MissingPropertyException("articles")

    return Pair(products, articles)
}

fun transform(value: HomeContentResponse.Product) = Product(
    value.name ?: throw MissingPropertyException("product.name"),
    value.image ?: throw MissingPropertyException("product.image"),
    value.link ?: throw MissingPropertyException("product.link")
)

fun transform(value: HomeContentResponse.Article) = Article(
    value.title ?: throw MissingPropertyException("article.name"),
    value.image ?: throw MissingPropertyException("article.image"),
    value.link ?: throw MissingPropertyException("article.link")
)
