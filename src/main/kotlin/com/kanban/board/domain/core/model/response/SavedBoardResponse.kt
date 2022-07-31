package com.kanban.board.domain.core.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.*

data class SavedBoardResponse (
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("created_at")
    val createdAt: OffsetDateTime
)