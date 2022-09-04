package com.kanban.board.infrastructure.configuration

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.kanban.board.domain.constant.SecurityConstants.EXPIRATION_TIME
import com.kanban.board.domain.constant.SecurityConstants.SECRET
import com.kanban.board.domain.core.model.response.user.authentication.AuthTokenResponse
import com.kanban.board.domain.core.model.response.user.authentication.UserDetailsResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val objectMapper: ObjectMapper
) : UsernamePasswordAuthenticationFilter() {

    init {
        this.authenticationManager = authenticationManager

        setFilterProcessesUrl("/public/api/v1/user/login")
    }

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        try {
            val credentials = objectMapper.readValue(request.inputStream, UserDetailsResponse::class.java)

            return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    credentials.username,
                    credentials.password,
                    arrayListOf()
                )
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user = (authResult?.principal as UserDetailsResponse)
        val token = JWT.create()
            .withSubject(user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SECRET))

        val responseBody = AuthTokenResponse(
            user.username,
            token
        )

        response?.writer?.write(objectMapper.writeValueAsString(responseBody))
        response?.writer?.flush()
    }

}