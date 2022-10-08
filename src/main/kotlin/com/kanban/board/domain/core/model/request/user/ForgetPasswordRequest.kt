package com.kanban.board.domain.core.model.request.user

import com.fasterxml.jackson.annotation.JsonProperty

data class ForgetPasswordRequest(
    @JsonProperty("email")
    val email: String
)
