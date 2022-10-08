package com.kanban.board.shared.utils

private val UPPERCASE_CHARS = ('A'..'Z')
private val LOWERCASE_CHARS = ('a'..'z')
private val NUMERIC_CHARS = ('0'..'9')

fun randomString(length: Int): String {
    val allowedChars = UPPERCASE_CHARS + LOWERCASE_CHARS + NUMERIC_CHARS
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}