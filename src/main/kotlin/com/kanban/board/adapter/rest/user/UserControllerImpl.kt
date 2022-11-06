package com.kanban.board.adapter.rest.user

import com.kanban.board.domain.core.model.request.user.UpdateUserRequest
import com.kanban.board.domain.core.model.response.user.UserResponse
import com.kanban.board.domain.core.model.response.user.authentication.AuthTokenResponse
import com.kanban.board.domain.port.rest.user.UserController
import com.kanban.board.domain.port.service.user.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@Tag(name = "Public User", description = "Provides user public apis")
class UserControllerImpl(
    val userService: UserService
): UserController {

    override fun me(): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.me())
    }

    override fun update(userId: UUID, updateUserRequest: UpdateUserRequest): ResponseEntity<Any> {
        userService.update(userId, updateUserRequest)
        return ResponseEntity.noContent().build()
    }

    override fun refreshBearerToken(): ResponseEntity<AuthTokenResponse> {
        return ResponseEntity.ok(userService.refreshBearerToken())
    }

}