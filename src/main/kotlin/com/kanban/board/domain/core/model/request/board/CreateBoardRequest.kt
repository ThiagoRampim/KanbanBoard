package com.kanban.board.domain.core.model.request.board

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateBoardRequest(
    @JsonProperty("name")
    val name: String
)
