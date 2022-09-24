package com.kanban.board.domain.core.model.response.board

import com.fasterxml.jackson.annotation.JsonProperty
import com.kanban.board.domain.enums.TagTypeEnum
import java.time.OffsetDateTime
import java.util.*

data class SaveTagResponse(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("title")
    val title: String?,

    @JsonProperty("type")
    val type: TagTypeEnum,

    @JsonProperty("color")
    val color: String,

    @JsonProperty("board_id")
    val boardId: UUID,

    @JsonProperty("created_at")
    val createdAt: OffsetDateTime
)
