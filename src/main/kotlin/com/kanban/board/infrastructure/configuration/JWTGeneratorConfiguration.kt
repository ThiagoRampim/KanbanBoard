package com.kanban.board.infrastructure.configuration

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.kanban.board.domain.constant.SecurityConstants
import com.kanban.board.domain.core.model.response.user.authentication.AuthTokenResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class JWTGeneratorConfiguration {
    fun generateJWT(userName: String): AuthTokenResponse {
        val token = JWT.create()
            .withSubject(userName)
            .withExpiresAt(Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SecurityConstants.SECRET))

        return AuthTokenResponse(
            userName,
            token
        )
    }
}