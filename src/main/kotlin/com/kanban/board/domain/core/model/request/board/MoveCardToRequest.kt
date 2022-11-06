package com.kanban.board.domain.core.model.request.board

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class MoveCardToRequest(
    @JsonProperty("column_id")
    val columnId: UUID
)
