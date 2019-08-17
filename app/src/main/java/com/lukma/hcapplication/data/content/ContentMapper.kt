package com.lukma.hcapplication.data.content

import com.lukma.hcapplication.data.common.MissingPropertyException
import com.lukma.hcapplication.data.content.cloud.HomeContentResponse
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.HomeContent
import com.lukma.hcapplication.domain.content.Product

fun transform(value: HomeContentResponse): HomeContent {
    val products = value.data
        ?.find { it.section == HomeContentResponse.SectionType.PRODUCTS }
        ?.let { it as HomeContentResponse.Section.ProductSection }
        ?.items?.map(::transform)
        ?: throw MissingPropertyException("products")

    var articleSectionName = ""
    val articles = value.data
        .find { it.section == HomeContentResponse.SectionType.ARTICLES }
        ?.let { it as HomeContentResponse.Section.ArticleSection }
        ?.also { articleSectionName = it.title ?: throw MissingPropertyException("products.title") }
        ?.items?.map(::transform)
        ?: throw MissingPropertyException("articles")

    return HomeContent(
        HomeContent.ProductSection(products),
        HomeContent.ArticleSection(articleSectionName, articles)
    )
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
