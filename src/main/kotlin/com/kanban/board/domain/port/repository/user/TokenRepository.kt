package com.kanban.board.domain.port.repository.user

import com.kanban.board.domain.core.model.entity.user.Token
import com.kanban.board.domain.enums.user.TokenTypeEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime
import java.util.UUID

@Repository
interface TokenRepository : JpaRepository<Token, UUID> {

    @Query(
        """
            SELECT token
            FROM Token AS token
            INNER JOIN FETCH token.user AS user
            WHERE token.type = :type
            AND token.token = :token
        """
    )
    fun findByTypeAndToken(type: TokenTypeEnum, token: String): Token?

    @Transactional
    @Modifying
    fun deleteAllByUserIdAndType(userId: UUID, type: TokenTypeEnum)

    @Transactional
    @Modifying
    fun deleteAllByValidUntilBefore(dateTime: OffsetDateTime)

}