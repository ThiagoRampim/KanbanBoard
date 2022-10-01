package com.kanban.board.domain.port.repository.user

import com.kanban.board.domain.core.model.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    fun findByEmail(email: String): User?

    @Query(
        """
            SELECT user
            FROM User AS user
            INNER JOIN user.boardRelations AS userBoard
            INNER JOIN userBoard.board AS board
            WHERE user.id IN :userIds
            AND board.id = :boardId
        """
    )
    fun findAllByIdAndBoardId(userIds: List<UUID>, boardId: UUID): List<User>

    @Query(
        """
            SELECT COUNT(board.id) > 0 
            FROM User AS user
            LEFT JOIN user.boardRelations AS userBoard
            LEFT JOIN userBoard.board AS board
            WHERE user.email = :userEmail
            AND board.id = :companyId
        """
    )
    fun userHasAccessToBoardByEmail(userEmail: String, companyId: UUID): Boolean

}