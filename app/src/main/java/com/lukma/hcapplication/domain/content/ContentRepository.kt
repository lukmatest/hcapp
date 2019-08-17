package com.lukma.hcapplication.domain.content

interface ContentRepository {
    suspend fun getHomeContent(): HomeContent
}
