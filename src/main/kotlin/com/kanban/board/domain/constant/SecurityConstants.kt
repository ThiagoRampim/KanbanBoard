package com.kanban.board.domain.constant

object SecurityConstants {
    val SECRET = "lBz64DHasAXfjNhK3YmRIrC5jHAiF3AA"
    val EXPIRATION_TIME = 900_000 // 15 minutes in milliseconds
    val TOKEN_PREFIX = "Bearer "
    val HEADER_STRING = "Authorization"
}