package com.kanban.board.domain.port.rest.user

import com.kanban.board.domain.core.model.request.user.UpdateUserRequest
import com.kanban.board.domain.core.model.response.user.UserResponse
import com.kanban.board.domain.core.model.response.user.authentication.AuthTokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@RequestMapping("/api/v1/user")
interface UserController {

    @GetMapping("/me")
    fun me(): ResponseEntity<UserResponse>

    @PutMapping("/{userId}")
    fun update(
        @PathVariable("userId") userId: UUID,
        @RequestBody updateUserRequest: UpdateUserRequest
    ): ResponseEntity<Any>

    @PutMapping("/refresh-token")
    fun refreshBearerToken(): ResponseEntity<AuthTokenResponse>

}