package com.example.droidchat.domain.mapper

import com.example.droidchat.data.model.exception.NetworkException
import com.example.droidchat.domain.model.exception.ExceptionPresentation

internal object ExceptionMapper {

    fun NetworkException.toExceptionPresentation(): ExceptionPresentation {
        return when (this) {
            is NetworkException.NotFoundException -> ExceptionPresentation.NotFound
            is NetworkException.ConflictException -> ExceptionPresentation.Conflict
            is NetworkException.BadRequestException -> ExceptionPresentation.BadRequest
            is NetworkException.UnauthorizedException -> ExceptionPresentation.Unauthorized
            is NetworkException.ServerErrorException -> ExceptionPresentation.ServerError
            is NetworkException.UnprocessableEntityException -> ExceptionPresentation.UnprocessableEntity
            else -> ExceptionPresentation.UnknownError
        }
    }
}