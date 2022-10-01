package com.kanban.board.domain.core.model.response.user

import com.fasterxml.jackson.annotation.JsonProperty

data class UserSimpleResponse(
    @JsonProperty("user_name")
    val userName: String,

    @JsonProperty("photo_url")
    val photoUrl: String?
)
