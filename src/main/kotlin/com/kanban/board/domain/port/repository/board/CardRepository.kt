package com.kanban.board.domain.port.repository.board

import com.kanban.board.domain.core.model.entity.board.Card
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
            LEFT JOIN FETCH card.cardTag AS cardTag
            LEFT JOIN FETCH cardTag.tag AS tag
            WHERE card.id = :cardId
            AND boardColumn.id = :boardColumnId
            AND board.id = :boardId
        """
    )
    fun findByIdAndBoardColumnIdAndBoardId(cardId: UUID, boardColumnId: UUID, boardId: UUID): Card?

    @Query(
        value = """
            SELECT card
            FROM Card AS card
            INNER JOIN FETCH card.boardColumn AS boardColumn
            INNER JOIN FETCH boardColumn.board AS board
            WHERE boardColumn.id = :boardColumnId
            AND board.id = :boardId
        """,
        countQuery = """
            SELECT COUNT(card)
            FROM Card AS card
            INNER JOIN card.boardColumn AS boardColumn
            INNER JOIN boardColumn.board AS board
            WHERE boardColumn.id = :boardColumnId
            AND board.id = :boardId
        """
    )
    fun findAllByBoardColumnIdAndColumnId(boardColumnId: UUID, boardId: UUID, pageable: Pageable): Page<Card>

}