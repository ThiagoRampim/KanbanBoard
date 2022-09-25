package com.kanban.board.infrastructure.extension

import org.springframework.security.core.context.SecurityContextHolder

fun isUserLogged() = SecurityContextHolder.getContext().authentication != null

fun currentUser() = SecurityContextHolder.getContext().authentication?.principal