package com.lukma.hcapplication.domain.content.usecase

import com.lukma.hcapplication.domain.common.BaseUseCase
import com.lukma.hcapplication.domain.content.ContentRepository
import com.lukma.hcapplication.domain.content.HomeContent

class GetHomeContentUseCase(private val repository: ContentRepository) :
    BaseUseCase<HomeContent>() {

    override suspend fun build() = repository.getHomeContent()
}
