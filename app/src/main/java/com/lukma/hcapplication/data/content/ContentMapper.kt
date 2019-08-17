package com.lukma.hcapplication.data.content

import com.lukma.hcapplication.data.common.MissingPropertyException
import com.lukma.hcapplication.data.content.cloud.ArticleItem
import com.lukma.hcapplication.data.content.cloud.HomeContentResponse
import com.lukma.hcapplication.data.content.cloud.ProductItem
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.HomeContent
import com.lukma.hcapplication.domain.content.Product

fun transform(value: HomeContentResponse): HomeContent {
    val productSection = value.data
        ?.find { it.section == HomeContentResponse.SectionType.PRODUCTS }
        ?.let { transform(it as HomeContentResponse.Section.ProductSection) }
        ?: throw MissingPropertyException("productSection")

    val articleSection = value.data
        .find { it.section == HomeContentResponse.SectionType.ARTICLES }
        ?.let { transform(it as HomeContentResponse.Section.ArticleSection) }
        ?: throw MissingPropertyException("articleSection")

    return HomeContent(productSection, articleSection)
}

fun transform(value: HomeContentResponse.Section.ProductSection) = HomeContent.ProductSection(
    value.items?.map { transform(it) } ?: throw MissingPropertyException("productSection.items")
)

fun transform(value: HomeContentResponse.Section.ArticleSection) = HomeContent.ArticleSection(
    value.title ?: throw MissingPropertyException("articleSection.title"),
    value.items?.map { transform(it) } ?: throw MissingPropertyException("articleSection.items")
)

fun transform(value: ProductItem) = Product(
    value.name ?: throw MissingPropertyException("product.name"),
    value.image ?: throw MissingPropertyException("product.image"),
    value.link ?: throw MissingPropertyException("product.link")
)

fun transform(value: ArticleItem) = Article(
    value.title ?: throw MissingPropertyException("article.name"),
    value.image ?: throw MissingPropertyException("article.image"),
    value.link ?: throw MissingPropertyException("article.link")
)
