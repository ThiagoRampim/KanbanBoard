package com.kanban.board.adapter.service.user

import com.kanban.board.domain.core.model.entity.user.User
import com.kanban.board.domain.core.model.request.user.CreateUserRequest
import com.kanban.board.domain.port.repository.user.UserRepository
import com.kanban.board.domain.port.service.user.UserService
import com.kanban.board.infrastructure.configuration.EncryptConfiguration
import com.kanban.board.infrastructure.extension.currentUserEmail
import com.kanban.board.infrastructure.extension.currentUserEmailOrElseThrow
import com.kanban.board.shared.exception.BadRequestException
import org.springframework.stereotype.Service
import java.util.*

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

    override fun currentUserOrElseThrow(): User {
        return currentUser()
            ?: throw BadRequestException("Não foi identificado um usuário autenticado")
    }

    override fun currentUser(): User? {
        return currentUserEmail()?.let { userRepository.findByEmail(it) }
    }

    override fun currentUserHasAccessToBoard(boardId: UUID): Boolean {
        return userRepository.userHasAccessToBoardByEmail(userEmail = currentUserEmailOrElseThrow(), boardId)
    }

    override fun blockIfCurrentUserHasNotAccessToBoard(boardId: UUID, customError: Exception?) {
        if (!currentUserHasAccessToBoard(boardId)) {
            throw customError ?: BadRequestException("Usuário não tem acesso a este quadro")
        }
    }

}