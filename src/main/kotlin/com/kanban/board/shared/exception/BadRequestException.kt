package com.kanban.board.shared.exception

import org.springframework.http.HttpStatus

class BadRequestException(
    message: String,
    detail: Any? = null
) : BaseException(
    HttpStatus.BAD_REQUEST,
    message,
    detail
)