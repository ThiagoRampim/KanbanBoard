package com.kanban.board.domain.core.model.response.boardColumn

import com.fasterxml.jackson.annotation.JsonProperty
import com.kanban.board.domain.core.model.response.user.SimpleUserResponse
import java.util.UUID

data class SimpleCardResponse(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("title")
    val title: String,

    @JsonProperty("users")
    val users: List<SimpleUserResponse>
)
