package com.kanban.board.domain.core.model.request.user

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateUserRequest (
    @JsonProperty("first_name")
    val firstName: String,

    @JsonProperty("last_name")
    val lastName: String?,

    @JsonProperty("photo_url")
    val photoUrl: String?
)