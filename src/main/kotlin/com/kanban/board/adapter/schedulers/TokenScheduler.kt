package com.kanban.board.adapter.schedulers

import com.kanban.board.domain.port.repository.user.TokenRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.OffsetDateTime.now

@Service
class TokenScheduler(
    private val tokenRepository: TokenRepository
) {

    @Scheduled(cron = "0 30 * * * *")
    fun clearInvalidTokens() {
        tokenRepository.deleteAllByValidUntilBefore(now())
    }

}