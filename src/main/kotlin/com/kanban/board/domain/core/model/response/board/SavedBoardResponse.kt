package com.kanban.board.domain.core.model.response.board

import com.fasterxml.jackson.annotation.JsonProperty
import com.kanban.board.domain.core.model.response.user.SimpleUserResponse
import java.time.OffsetDateTime
import java.util.*
import kotlin.collections.List

data class SavedBoardResponse(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("created_at")
    val createdAt: OffsetDateTime,

    @JsonProperty("columns")
    val columns: List<SavedBoardColumnResponse>,

    @JsonProperty("tags")
    val tags: List<SavedTagResponse>,

    @JsonProperty("members")
    val members: List<SimpleUserResponse>
)