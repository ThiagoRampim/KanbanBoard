package com.kanban.board.domain.port.repository.board

import com.kanban.board.domain.core.model.entity.board.Card
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CardRepository: JpaRepository<Card, UUID> {

    @Query(
        """
            SELECT card
            FROM Card AS card
            INNER JOIN FETCH card.boardColumn AS boardColumn
            INNER JOIN FETCH boardColumn.board AS board
            WHERE card.id = :cardId
            AND board.id = :boardId
        """
    )
    fun findByIdAndBoardId(cardId: UUID, boardId: UUID): Card?

}