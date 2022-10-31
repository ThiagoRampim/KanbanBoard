package com.kanban.board.domain.core.model.response.board

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class SimpleBoardResponse(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("name")
    val name: String
)