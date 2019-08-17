package com.lukma.hcapplication.data.module

import com.lukma.hcapplication.data.content.ContentDataRepository
import com.lukma.hcapplication.domain.content.ContentRepository
import org.koin.dsl.module
import org.koin.experimental.builder.factoryBy

val repositoryDataModule = module {
    factoryBy<ContentRepository, ContentDataRepository>()
}
