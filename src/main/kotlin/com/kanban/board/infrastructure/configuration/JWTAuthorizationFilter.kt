package com.kanban.board.infrastructure.configuration

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.kanban.board.domain.constant.SecurityConstants.HEADER_STRING
import com.kanban.board.domain.constant.SecurityConstants.SECRET
import com.kanban.board.domain.constant.SecurityConstants.TOKEN_PREFIX
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(
    authManager: AuthenticationManager
) : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val header = request.getHeader(HEADER_STRING)

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }

        SecurityContextHolder.getContext().authentication = getAuthentication(request)
        chain.doFilter(request, response)
    }

    private fun getAuthentication(
        request: HttpServletRequest
    ): UsernamePasswordAuthenticationToken? {
        val token: String? = request.getHeader(HEADER_STRING)?.replace(TOKEN_PREFIX, "")

        if (token != null) {
            val user = JWT.require(Algorithm.HMAC512(SECRET))
                .build()
                .verify(token)
                .subject

            if (user != null) {
                return UsernamePasswordAuthenticationToken(user, null, arrayListOf())
            }
        }

        return null
    }

}