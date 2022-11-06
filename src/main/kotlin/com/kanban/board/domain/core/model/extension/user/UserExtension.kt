package com.kanban.board.domain.core.model.extension.user

import com.kanban.board.domain.core.model.entity.user.User
import com.kanban.board.domain.core.model.response.user.SimpleUserResponse
import com.kanban.board.domain.core.model.response.user.UserResponse

fun User.toSimpleUserResponse() =
    SimpleUserResponse(
        id = this.id,
        userName = this.fullName,
        photoUrl = this.photoUrl
    )

fun User.toUserResponse() =
    UserResponse(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        photoUrl = this.photoUrl
    )