package com.kanban.board.domain.port.rest.user

import com.kanban.board.domain.core.model.request.user.CreateUserRequest
import com.kanban.board.domain.core.model.request.user.ForgetPasswordRequest
import com.kanban.board.domain.core.model.request.user.OverwritePasswordRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/public/api/v1/user")
interface UserPublicController {

    @PostMapping
    fun create(
        @RequestBody createUserRequest: CreateUserRequest
    ): ResponseEntity<Any>

    @PostMapping("/forget-password")
    fun forgetPassword(
        @RequestBody forgetPasswordRequest: ForgetPasswordRequest
    ): ResponseEntity<Any>

    @PutMapping("/overwrite-password")
    fun overwritePassword(
        @RequestBody overwritePasswordRequest: OverwritePasswordRequest
    ): ResponseEntity<Any>

}