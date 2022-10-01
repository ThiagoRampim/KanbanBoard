package com.kanban.board.domain.core.model.response.user

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class UserSimpleResponse(
    @JsonProperty("user_id")
    val id: UUID,

    @JsonProperty("user_name")
    val userName: String,

    @JsonProperty("photo_url")
    val photoUrl: String?
)
