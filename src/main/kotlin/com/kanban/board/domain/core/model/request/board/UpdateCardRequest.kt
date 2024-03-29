package com.kanban.board.domain.core.model.request.board

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.UUID

data class UpdateCardRequest(
    @JsonProperty("title")
    val title: String,

    @JsonProperty("description")
    val description: String? = null,

    @JsonProperty("priority")
    val priority: Int,

    @JsonProperty("start_date")
    val startDate: OffsetDateTime? = null,

    @JsonProperty("end_date")
    val endDate: OffsetDateTime? = null,

    @JsonProperty("concluded_at")
    val concludedAt: OffsetDateTime? = null,

    @JsonProperty("column_id")
    val columnId: UUID
)
