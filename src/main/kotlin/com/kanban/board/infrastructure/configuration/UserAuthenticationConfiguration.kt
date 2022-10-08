package com.kanban.board.infrastructure.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "user-authentication")
data class UserAuthenticationConfiguration(
    var newUserConfirmationEmailRootLink: String?,
    var rewritePasswordRootLink: String?
)
