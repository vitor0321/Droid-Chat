package com.example.droidchat.util

import com.example.droidchat.data.model.exception.NetworkException
import com.example.droidchat.domain.mapper.ExceptionMapper.toExceptionPresentation
import com.example.droidchat.domain.model.exception.ExceptionPresentation

internal suspend fun handleError(
    exception: Throwable,
    isLoading: (Boolean) -> Unit,
    emitError: suspend (String) -> Unit
) {
    isLoading(false)
    val error = if (exception is NetworkException) {
        exception.toExceptionPresentation()
    } else {
        ExceptionPresentation.UnknownError
    }
    emitError(error.message)
}