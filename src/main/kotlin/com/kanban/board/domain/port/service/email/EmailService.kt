package com.kanban.board.domain.port.service.email

interface EmailService {

    fun sendNewUserConfirmationEmail(toEmail: String, token: String)

    fun sendForgetPasswordEmail(toEmail: String, token: String)

    fun sendEmail(
        subject: String,
        message: String,
        toEmail: String
    )

}