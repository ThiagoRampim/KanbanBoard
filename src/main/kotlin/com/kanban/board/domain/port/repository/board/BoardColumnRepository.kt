package com.kanban.board.domain.port.repository.board

import com.kanban.board.domain.core.model.entity.board.BoardColumn
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BoardColumnRepository: JpaRepository<BoardColumn, UUID> {

    @Query(
        """
            SELECT boardColumn
            FROM BoardColumn AS boardColumn
            INNER JOIN FETCH boardColumn.board AS board
            WHERE boardColumn.id = :boardColumnId
            AND board.id = :boardId
        """
    )
    fun findByIdAndBoardId(boardColumnId: UUID, boardId: UUID): BoardColumn?

}