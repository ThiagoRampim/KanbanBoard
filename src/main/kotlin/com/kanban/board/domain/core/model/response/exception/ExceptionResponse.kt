package com.kanban.board.domain.core.model.response.exception

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

data class ExceptionResponse(
    @JsonProperty("timestamp")
    val timestamp: OffsetDateTime,

    @JsonProperty("status")
    val status: Int,

    @JsonProperty("path")
    val path: String?,

    @JsonProperty("message")
    val message: String,

    @JsonProperty("details")
    val details: Any?
)
