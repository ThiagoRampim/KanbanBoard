package com.kanban.board.adapter.service.user

import com.kanban.board.domain.core.model.entity.user.User
import com.kanban.board.domain.core.model.request.user.CreateUserRequest
import com.kanban.board.domain.port.repository.user.UserRepository
import com.kanban.board.domain.port.service.user.UserService
import com.kanban.board.infrastructure.configuration.EncryptConfiguration
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val encryptConfiguration: EncryptConfiguration,
) : UserService {

    override fun create(createUserRequest: CreateUserRequest) {
        val newUser = User(
            firstName = createUserRequest.firstName,
            lastName = createUserRequest.lastName,
            email = createUserRequest.email,
            isActive = true,
            password = encryptConfiguration.bCryptPasswordEncoder().encode(createUserRequest.password)
        )
        userRepository.save(newUser)
    }

}