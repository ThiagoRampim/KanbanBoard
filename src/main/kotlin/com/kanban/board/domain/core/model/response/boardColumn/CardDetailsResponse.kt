package com.kanban.board.domain.core.model.response.boardColumn

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.*

data class CardDetailsResponse(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("title")
    val title: String,

    @JsonProperty("description")
    val description: String?,

    @JsonProperty("start_date")
    val startDate: OffsetDateTime?,

    @JsonProperty("end_date")
    val endDate: OffsetDateTime?,

    @JsonProperty("concluded_at")
    val concludedAt: OffsetDateTime?,

    @JsonProperty("created_at")
    val createdAt: OffsetDateTime
)
