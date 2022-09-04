package com.kanban.board.domain.core.model.response.user.authentication

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthTokenResponse(
    @JsonProperty("email")
    val email: String,

    @JsonProperty("access_token")
    val accessToken: String
)