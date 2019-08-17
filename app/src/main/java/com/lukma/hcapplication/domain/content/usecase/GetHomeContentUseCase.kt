package com.lukma.hcapplication.domain.content.usecase

import com.lukma.hcapplication.domain.common.BaseUseCase
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.ContentRepository
import com.lukma.hcapplication.domain.content.Product

class GetHomeContentUseCase(private val repository: ContentRepository) :
    BaseUseCase<Pair<List<Product>, List<Article>>>() {

    override suspend fun build() = repository.getHomeContent()
}
