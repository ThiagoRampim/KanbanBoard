package com.kanban.board.domain.port.rest.user

import com.kanban.board.domain.core.model.request.user.CreateUserRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/public/api/v1/user")
interface UserPublicController {

    @PostMapping
    fun create(
        @RequestBody createUserRequest: CreateUserRequest
    ): ResponseEntity<Any>

}