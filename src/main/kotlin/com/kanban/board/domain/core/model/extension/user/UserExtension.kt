package com.kanban.board.domain.core.model.extension.user

import com.kanban.board.domain.core.model.entity.user.User
import com.kanban.board.domain.core.model.response.user.SimpleUserResponse

fun User.toSimpleUserResponse() =
    SimpleUserResponse(
        id = this.id,
        userName = this.fullName,
        photoUrl = this.photoUrl
    )