package com.lukma.hcapplication.domain.content

interface ContentRepository {
    suspend fun getHomeContent(): Pair<List<Product>, List<Article>>
}
