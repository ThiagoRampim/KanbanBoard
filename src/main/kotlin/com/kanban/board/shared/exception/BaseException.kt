package com.kanban.board.shared.exception

import org.springframework.http.HttpStatus

abstract class BaseException(
    val status: HttpStatus,
    message: String,
    val detail: Any? = "No details avaliable"
) : RuntimeException(message)