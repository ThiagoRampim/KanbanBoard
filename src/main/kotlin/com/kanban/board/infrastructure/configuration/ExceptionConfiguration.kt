package com.kanban.board.infrastructure.configuration

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.kanban.board.domain.core.model.response.exception.ExceptionResponse
import com.kanban.board.shared.exception.BaseException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.OffsetDateTime

@ControllerAdvice
class ExceptionConfiguration: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [Exception::class])
    fun handleConflict(exception: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        logger.error(exception)
        return buildExceptionResponse(exception, request)
    }

    @ExceptionHandler(value = [HttpMessageConversionException::class])
    fun handleHttpMessageConversionException(
        exception: HttpMessageConversionException,
        request: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        logger.error(exception)

        val message = if (exception.cause is MissingKotlinParameterException) {
            "Field is missing or null ${(exception.cause as MissingKotlinParameterException).parameter}"
        } else {
            "Required field is missing or null"
        }
        return buildExceptionResponse(request, HttpStatus.BAD_REQUEST, message)
    }

    @ExceptionHandler(value = [MethodArgumentTypeMismatchException::class])
    fun handleMethodArgumentTypeMismatchException(
        exception: MethodArgumentTypeMismatchException,
        request: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        logger.error(exception)

        return buildExceptionResponse(
            request,
            HttpStatus.BAD_REQUEST,
            "Parameter is missing or invalid '${exception.name}'",
            mapOf(
                "send_value" to "${exception.value}",
                "expected_type" to "${exception.requiredType?.simpleName}"
            )
        )
    }

    @ExceptionHandler(value = [MissingRequestHeaderException::class])
    fun handleMissingRequestHeaderException(
        exception: MissingRequestHeaderException,
        request: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        logger.error(exception)

        return buildExceptionResponse(
            request,
            HttpStatus.BAD_REQUEST,
            "Missing header ${exception.headerName}",
            mapOf(
                "expected type" to exception.parameter.parameterType.simpleName
            )
        )
    }

    override fun handleHttpMessageNotReadable(
        exception: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        logger.error(exception)

        val message: String
        val details: Any?
        if (exception.cause is InvalidFormatException) {
            val cause = (exception.cause as InvalidFormatException)
            val fieldWithError = cause.path.joinToString(".") { it.fieldName }
            message = "Parameter $fieldWithError is missing or invalid"
            details = mapOf(
                "send_value" to "${cause.value}",
                "expected_type" to cause.targetType.simpleName
            )
        } else {
            message = "Invalid request json"
            details = null
        }
        return buildExceptionResponse(request, HttpStatus.BAD_REQUEST, message, details) as ResponseEntity<Any>
    }

    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        logger.error(exception)

        return buildExceptionResponse(
            request = request,
            status = status,
            message = "Validation failed for object ${exception.bindingResult.objectName}"
        ) as ResponseEntity<Any>
    }

    override fun handleExceptionInternal(
        exception: java.lang.Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        logger.error(exception)

        return buildExceptionResponse(
            request = request,
            status = status,
            message = "No message available"
        ) as ResponseEntity<Any>
    }

    private fun buildExceptionResponse(
        exception: Exception,
        request: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        val statusValue: HttpStatus
        val detailValue: Any?

        if (exception is BaseException) {
            statusValue = exception.status
            detailValue = exception.detail
        } else {
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR
            detailValue = null
        }

        return buildExceptionResponse(
            request = request,
            status = statusValue,
            message = exception.message ?: "No message available",
            details = detailValue
        )
    }

    private fun buildExceptionResponse(
        request: WebRequest,
        status: HttpStatus,
        message: String,
        details: Any? = null
    ): ResponseEntity<ExceptionResponse> {
        return ResponseEntity
            .status(status)
            .body(
                ExceptionResponse(
                    timestamp = OffsetDateTime.now(),
                    status = status.value(),
                    path = if (request is ServletWebRequest) request.request.requestURI else null,
                    message = message,
                    details = details
                )
            )
    }

}