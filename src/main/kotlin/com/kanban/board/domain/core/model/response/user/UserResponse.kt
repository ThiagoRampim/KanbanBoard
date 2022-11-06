package com.kanban.board.domain.core.model.response.user

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class UserResponse(
    @JsonProperty("id")
    val id: UUID,

    @JsonProperty("first_name")
    val firstName: String,

    @JsonProperty("last_name")
    val lastName: String?,

    @JsonProperty("email")
    val email: String?,

    @JsonProperty("photo_url")
    val photoUrl: String?
)
