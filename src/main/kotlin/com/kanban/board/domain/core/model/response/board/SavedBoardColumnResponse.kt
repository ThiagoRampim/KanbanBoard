package com.kanban.board.domain.core.model.response.board

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.UUID

data class SavedBoardColumnResponse(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("position")
    val position: Int,

    @JsonProperty("created_at")
    val createdAt: OffsetDateTime
)
