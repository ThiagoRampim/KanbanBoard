package com.kanban.board.shared.exception

import org.springframework.http.HttpStatus

class InternalServerErrorException(
    message: String,
    detail: Any? = null
) : BaseException(
    HttpStatus.INTERNAL_SERVER_ERROR,
    message,
    detail
)