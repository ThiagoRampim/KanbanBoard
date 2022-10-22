package com.kanban.board.infrastructure.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.AuthenticationEntryPoint
import java.time.OffsetDateTime.now
import java.time.ZoneOffset

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val encryptConfiguration: EncryptConfiguration,
    private val objectMapper: ObjectMapper
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .headers().frameOptions().deny()
            .and()
            .cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
            .and()
            .authorizeHttpRequests()
            .mvcMatchers("/public/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(JWTAuthenticationFilter(authenticationManager(), objectMapper))
            .addFilter(JWTAuthorizationFilter(authenticationManager()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encryptConfiguration.bCryptPasswordEncoder())
    }

    @Bean
    fun restAuthenticationEntryPoint(): AuthenticationEntryPoint? {
        return AuthenticationEntryPoint { _, response, _ ->
            val errorObject: MutableMap<String, Any> = HashMap()
            errorObject["message"] = "Access Denied"
            errorObject["error"] = HttpStatus.UNAUTHORIZED
            errorObject["code"] = 401
            errorObject["timestamp"] = now(ZoneOffset.UTC)

            response.contentType = "application/json;charset=UTF-8"
            response.status = 401
            response.writer.write(objectMapper.writeValueAsString(errorObject))
        }
    }

}