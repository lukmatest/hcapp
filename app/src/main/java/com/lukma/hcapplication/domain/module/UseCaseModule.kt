package com.lukma.hcapplication.domain.module

import com.lukma.hcapplication.domain.content.usecase.GetHomeContentUseCase
import org.koin.dsl.module
import org.koin.experimental.builder.factory

val useCaseModule = module {
    factory<GetHomeContentUseCase>()
}
