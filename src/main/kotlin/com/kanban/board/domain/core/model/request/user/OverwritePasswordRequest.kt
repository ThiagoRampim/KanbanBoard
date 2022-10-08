package com.kanban.board.domain.core.model.request.user

import com.fasterxml.jackson.annotation.JsonProperty

data class OverwritePasswordRequest(
    @JsonProperty("token")
    val token: String,

    @JsonProperty("password")
    val password: String
)
