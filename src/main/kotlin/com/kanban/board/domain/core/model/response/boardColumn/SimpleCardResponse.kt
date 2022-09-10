package com.kanban.board.domain.core.model.response.boardColumn

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

// TODO(UMCOMPLETED RESPONSE, COMPLEMENT LATER!)
data class SimpleCardResponse(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("title")
    val title: String
)
