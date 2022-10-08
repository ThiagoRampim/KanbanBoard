package com.kanban.board.domain.port.service.user

import com.kanban.board.domain.core.model.entity.user.User
import com.kanban.board.domain.core.model.request.user.CreateUserRequest
import com.kanban.board.domain.core.model.request.user.ForgetPasswordRequest
import com.kanban.board.domain.core.model.request.user.OverwritePasswordRequest
import java.util.UUID

interface UserService {

    fun create(createUserRequest: CreateUserRequest)

    fun forgetPassword(forgetPasswordRequest: ForgetPasswordRequest)

    fun overwritePassword(overwritePasswordRequest: OverwritePasswordRequest)

    fun currentUserOrElseThrow(): User

    fun currentUser(): User?

    fun currentUserHasAccessToBoard(boardId: UUID): Boolean

    fun blockIfCurrentUserHasNotAccessToBoard(boardId: UUID, customError: Exception? = null)

}