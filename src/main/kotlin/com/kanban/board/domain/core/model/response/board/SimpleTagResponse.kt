package com.kanban.board.domain.core.model.response.board

import com.fasterxml.jackson.annotation.JsonProperty
import com.kanban.board.domain.enums.TagTypeEnum
import java.util.*

data class SimpleTagResponse(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("title")
    val title: String?,

    @JsonProperty("type")
    val type: TagTypeEnum,

    @JsonProperty("color")
    val color: String
)