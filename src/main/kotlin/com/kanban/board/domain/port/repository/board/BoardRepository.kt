package com.kanban.board.domain.port.repository.board

import com.kanban.board.domain.core.model.entity.board.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import org.springframework.data.jpa.repository.Query

@Repository
interface BoardRepository : JpaRepository<Board, UUID> {

    @Query(
        """
            SELECT board
            FROM Board AS board
            INNER JOIN FETCH board.columns AS column 
            WHERE board.id = :boardId
        """
    )
    fun findByIdFetched(boardId: UUID): Board?

}