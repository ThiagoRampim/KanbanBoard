package com.kanban.board.domain.core.model.request.board

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateTagRequest(
    @JsonProperty("title")
    val title: String,

    @JsonProperty("color")
    val color: String
)
