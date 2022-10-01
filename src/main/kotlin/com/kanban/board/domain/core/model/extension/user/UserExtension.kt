package com.kanban.board.domain.core.model.extension.user

import com.kanban.board.domain.core.model.entity.user.User
import com.kanban.board.domain.core.model.response.user.UserSimpleResponse

fun User.toSimpleUserResponse() =
    UserSimpleResponse(
        userName = this.fullName,
        photoUrl = this.photoUrl
    )