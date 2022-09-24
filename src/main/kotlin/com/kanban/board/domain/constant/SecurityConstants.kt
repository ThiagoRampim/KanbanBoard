package com.kanban.board.domain.constant

object SecurityConstants {
    const val SECRET = "lBz64DHasAXfjNhK3YmRIrC5jHAiF3AA"
    const val EXPIRATION_TIME = 900_000 // 15 minutes in milliseconds
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
}