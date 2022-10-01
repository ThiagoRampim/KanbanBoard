package com.kanban.board.domain.core.model.request.board

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class UpdateCardTagsRequest(
    @JsonProperty("add_tags")
    val addTags: List<UUID>,

    @JsonProperty("remove_tags")
    val removeTags: List<UUID>
)
