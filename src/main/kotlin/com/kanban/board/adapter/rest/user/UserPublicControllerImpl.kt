package com.kanban.board.adapter.rest.user

import com.kanban.board.domain.core.model.request.user.CreateUserRequest
import com.kanban.board.domain.core.model.request.user.ForgetPasswordRequest
import com.kanban.board.domain.core.model.request.user.OverwritePasswordRequest
import com.kanban.board.domain.port.rest.user.UserPublicController
import com.kanban.board.domain.port.service.user.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Public User", description = "Provides user public apis")
class UserPublicControllerImpl(
    val userService: UserService
): UserPublicController {

    override fun create(createUserRequest: CreateUserRequest): ResponseEntity<Any> {
        userService.create(createUserRequest)
        return ResponseEntity.noContent().build()
    }

    override fun forgetPassword(forgetPasswordRequest: ForgetPasswordRequest): ResponseEntity<Any> {
        userService.forgetPassword(forgetPasswordRequest)
        return ResponseEntity.noContent().build()
    }

    override fun overwritePassword(overwritePasswordRequest: OverwritePasswordRequest): ResponseEntity<Any> {
        userService.overwritePassword(overwritePasswordRequest)
        return ResponseEntity.noContent().build()
    }

}