package com.kanban.board.domain.port.service.user

import com.kanban.board.domain.core.model.request.user.CreateUserRequest

interface UserService {

    fun create(createUserRequest: CreateUserRequest)

}