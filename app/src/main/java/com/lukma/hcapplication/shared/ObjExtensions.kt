package com.lukma.hcapplication.shared

import com.lukma.hcapplication.domain.common.Either
import com.lukma.hcapplication.presentation.common.Resource

fun <V> Either<Throwable, V>.asResource() = when (this) {
    is Either.Error -> Resource.Failure(this.error)
    is Either.Value -> Resource.Success(this.value)
}
