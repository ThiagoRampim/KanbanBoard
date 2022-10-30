package com.kanban.board.adapter.service.email

import com.kanban.board.domain.port.service.email.EmailService
import com.kanban.board.infrastructure.configuration.UserAuthenticationConfiguration
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class EmailServiceImpl(
    private val mailSender: JavaMailSender,
    private val userAuthenticationConfiguration: UserAuthenticationConfiguration,
    @Value("\${spring.mail.username}") private val applicationMailUsername: String
): EmailService {

    override fun sendNewUserConfirmationEmail(toEmail: String, token: String) {
        sendEmail(
            subject = "Confirme seu email",
            message = "Confirme seu email para concluir seu cadastro através do link:" +
                "\n${userAuthenticationConfiguration.newUserConfirmationEmailRootLink}/${token}",
            toEmail = toEmail
        )
    }

    override fun sendForgetPasswordEmail(toEmail: String, token: String) {
        sendEmail(
            subject = "Recuperação de senha",
            message = "Recupere sua senha através do link:" +
                "\n${userAuthenticationConfiguration.rewritePasswordRootLink}/${token}",
            toEmail = toEmail
        )
    }

    override fun sendEmail(subject: String, message: String, toEmail: String) {
        val simpleMailMessage = SimpleMailMessage()
        simpleMailMessage.setSubject(subject)
        simpleMailMessage.setText(message)
        simpleMailMessage.setTo(toEmail)
        simpleMailMessage.setFrom(applicationMailUsername)

        try {
            mailSender.send(simpleMailMessage)
        } catch(exception: Exception) {
            exception.printStackTrace()
        }
    }

}