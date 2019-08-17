package com.lukma.hcapplication.data.content

import com.lukma.hcapplication.data.content.cloud.ContentApi
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.ContentRepository
import com.lukma.hcapplication.domain.content.Product

class ContentDataRepository(private val contentApi: ContentApi) : ContentRepository {
    override suspend fun getHomeContent(): Pair<List<Product>, List<Article>> =
        contentApi.getHomeContent().let(::transform)
}
