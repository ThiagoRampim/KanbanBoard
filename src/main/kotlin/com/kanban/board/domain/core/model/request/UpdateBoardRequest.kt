package com.kanban.board.domain.core.model.request

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateBoardRequest(
    @JsonProperty("name")
    val name: String
)
