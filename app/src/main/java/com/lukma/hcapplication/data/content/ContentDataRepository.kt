package com.lukma.hcapplication.data.content

import com.lukma.hcapplication.data.content.cloud.ContentApi
import com.lukma.hcapplication.domain.content.ContentRepository

class ContentDataRepository(private val contentApi: ContentApi) : ContentRepository {
    override suspend fun getHomeContent() = contentApi.getHomeContent().let(::transform)
}
