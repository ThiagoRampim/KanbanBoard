package com.kanban.board.adapter.service.user

import com.kanban.board.domain.core.model.response.user.authentication.UserDetailsResponse
import com.kanban.board.domain.port.repository.user.UserRepository
import com.kanban.board.shared.exception.BadRequestException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw BadRequestException("Username cannot be null")
        }

        val user = userRepository.findByEmail(username)
        return UserDetailsResponse(
            user.email,
            user.password,
            user.isActive
        )
    }

}