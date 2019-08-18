package com.lukma.hcapplication.content

import com.lukma.hcapplication.data.content.cloud.ArticleItem
import com.lukma.hcapplication.data.content.cloud.HomeContentResponse
import com.lukma.hcapplication.data.content.cloud.ProductItem
import com.lukma.hcapplication.data.content.transform
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.HomeContent
import com.lukma.hcapplication.domain.content.Product
import org.junit.Assert.assertEquals
import org.junit.Test

class ContentMapperTest {

    @Test
    fun testTransform() {
        val response = HomeContentResponse(
            listOf(
                HomeContentResponse.Section.ProductSection(
                    listOf(ProductItem("name", "image", "link"))
                ),
                HomeContentResponse.Section.ArticleSection(
                    "sectionTitle",
                    listOf(ArticleItem("title", "image", "link"))
                )
            )
        )
        val result = transform(response)
        val expected = HomeContent(
            HomeContent.ProductSection(
                listOf(Product("name", "image", "link"))
            ),
            HomeContent.ArticleSection(
                "sectionTitle",
                listOf(Article("title", "image", "link"))
            )
        )
        assertEquals(result, expected)
    }
}