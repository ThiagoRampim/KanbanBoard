package com.kanban.board.domain.core.model.request.board

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateBoardRequest(
    @JsonProperty("name")
    val name: String
)
