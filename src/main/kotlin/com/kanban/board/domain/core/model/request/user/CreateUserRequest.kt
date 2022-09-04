package com.kanban.board.domain.core.model.request.user

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserRequest (
    @JsonProperty("first_name")
    val firstName: String,

    @JsonProperty("last_name")
    val lastName: String?,

    @JsonProperty("email")
    val email: String,

    @JsonProperty("password")
    val password: String
)