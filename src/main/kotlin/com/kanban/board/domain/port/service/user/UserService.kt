package com.kanban.board.domain.port.service.user

import com.kanban.board.domain.core.model.entity.user.User
import com.kanban.board.domain.core.model.request.user.CreateUserRequest
import com.kanban.board.domain.core.model.request.user.ForgetPasswordRequest
import com.kanban.board.domain.core.model.request.user.OverwritePasswordRequest
import com.kanban.board.domain.core.model.request.user.UpdateUserRequest
import com.kanban.board.domain.core.model.response.user.UserResponse
import com.kanban.board.domain.core.model.response.user.authentication.AuthTokenResponse
import java.util.UUID

interface UserService {

    fun me(): UserResponse

    fun create(createUserRequest: CreateUserRequest)

    fun update(userId: UUID, updateUserRequest: UpdateUserRequest)

    fun forgetPassword(forgetPasswordRequest: ForgetPasswordRequest)

    fun overwritePassword(overwritePasswordRequest: OverwritePasswordRequest)

    fun currentUserOrElseThrow(): User

    fun currentUser(): User?

    fun currentUserHasAccessToBoard(boardId: UUID): Boolean

    fun blockIfCurrentUserHasNotAccessToBoard(boardId: UUID, customError: Exception? = null)

    fun refreshBearerToken(): AuthTokenResponse

}