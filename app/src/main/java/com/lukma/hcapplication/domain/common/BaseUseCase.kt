package com.lukma.hcapplication.domain.common

import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<Entity> {
    abstract suspend fun build(): Entity

    suspend fun invoke(context: CoroutineContext) = withContext(context) {
        either(::build)
    }
}
