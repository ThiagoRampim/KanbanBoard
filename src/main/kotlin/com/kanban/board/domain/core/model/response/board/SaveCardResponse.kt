package com.kanban.board.domain.core.model.response.board

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.UUID

data class SaveCardResponse(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("title")
    val title: String,

    @JsonProperty("description")
    val description: String? = null,

    @JsonProperty("board_id")
    val boardId: UUID,

    @JsonProperty("column_id")
    val columnId: UUID,

    @JsonProperty("start_date")
    val startDate: OffsetDateTime? = null,

    @JsonProperty("end_date")
    val endDate: OffsetDateTime? = null,

    @JsonProperty("concluded_at")
    val concludedAt: OffsetDateTime? = null,

    @JsonProperty("created_at")
    val createdAt: OffsetDateTime? = null,

    @JsonProperty("tags")
    val tags: List<SimpleTagResponse> = listOf(),
)