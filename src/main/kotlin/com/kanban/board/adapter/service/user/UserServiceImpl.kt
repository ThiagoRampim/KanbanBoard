package com.kanban.board.adapter.service.user

import com.kanban.board.domain.core.model.entity.user.Token
import com.kanban.board.domain.core.model.entity.user.User
import com.kanban.board.domain.core.model.request.user.CreateUserRequest
import com.kanban.board.domain.core.model.request.user.ForgetPasswordRequest
import com.kanban.board.domain.core.model.request.user.OverwritePasswordRequest
import com.kanban.board.domain.enums.user.TokenTypeEnum.OVERWRITE_PASSWORD
import com.kanban.board.domain.port.repository.user.TokenRepository
import com.kanban.board.domain.port.repository.user.UserRepository
import com.kanban.board.domain.port.service.email.EmailService
import com.kanban.board.domain.port.service.user.UserService
import com.kanban.board.infrastructure.configuration.EncryptConfiguration
import com.kanban.board.infrastructure.extension.currentUserEmail
import com.kanban.board.infrastructure.extension.currentUserEmailOrElseThrow
import com.kanban.board.shared.exception.BadRequestException
import com.kanban.board.shared.exception.InternalServerErrorException
import com.kanban.board.shared.utils.randomString
import org.springframework.stereotype.Service
import java.time.OffsetDateTime.now
import java.util.*

@Service
class UserServiceImpl(
    private val emailService: EmailService,
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val encryptConfiguration: EncryptConfiguration,
) : UserService {

    override fun create(createUserRequest: CreateUserRequest) {
        val newUser = User(
            firstName = createUserRequest.firstName,
            lastName = createUserRequest.lastName,
            email = createUserRequest.email,
            isActive = true,
            password = encodeUserPassword(createUserRequest.password)
        )
        userRepository.save(newUser)
    }

    override fun forgetPassword(forgetPasswordRequest: ForgetPasswordRequest) {
        val user = userRepository.findByEmail(forgetPasswordRequest.email)
            ?: throw BadRequestException("Email não cadastrado")

        val token = Token(
            type = OVERWRITE_PASSWORD,
            token = randomString(250),
            validUntil = now().plusHours(1),
            user = user
        )
        tokenRepository.save(token)

        emailService.sendForgetPasswordEmail(forgetPasswordRequest.email, token.token)
    }

    override fun overwritePassword(overwritePasswordRequest: OverwritePasswordRequest) {
        val token = try {
            tokenRepository.findByTypeAndToken(
                OVERWRITE_PASSWORD,
                overwritePasswordRequest.token
            )
        } catch (ex: Exception) {
            throw InternalServerErrorException("Houve um erro com o token informado, por favor, solicite um novo ou " +
                "tente novamente mais tarde")
        }

        if (token == null) {
            throw BadRequestException("Token inválido")
        }
        if (token.validUntil <= now()) {
            throw BadRequestException("Token vencido")
        }

        val user = token.user
        user.apply {
            this.password = encodeUserPassword(overwritePasswordRequest.password)
        }
        userRepository.save(user)

        tokenRepository.deleteAllByUserIdAndType(user.id, OVERWRITE_PASSWORD)
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

    private fun encodeUserPassword(password: String): String {
        return encryptConfiguration.bCryptPasswordEncoder().encode(password)
    }

}