package com.kanban.board.infrastructure.extension

import com.kanban.board.shared.exception.BadRequestException
import org.springframework.security.core.context.SecurityContextHolder

fun isUserLogged() = (SecurityContextHolder.getContext().authentication as String) != "anonymousUser"

fun currentUserEmail(): String? {
    val userEmail = SecurityContextHolder.getContext().authentication?.principal as String
    return if (userEmail == "anonymousUser") {
        null
    } else {
        userEmail
    }
}

fun currentUserEmailOrElseThrow(): String {
    return currentUserEmail()
        ?: throw BadRequestException("Não foi identificado um usuário autenticado")
}
