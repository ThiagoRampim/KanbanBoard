package com.kanban.board.domain.port.repository.board

import com.kanban.board.domain.core.model.entity.board.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TagRepository : JpaRepository<Tag, UUID> {

    fun findAllByBoardId(boardId: UUID): List<Tag>

    @Query(
        """
            SELECT tag
            FROM Tag AS tag
            INNER JOIN tag.board AS board
            WHERE tag.id in :tagId
            AND board.id = :boardId
        """
    )
    fun findAllByIdAndBoardId(tagId: List<UUID>, boardId: UUID): List<Tag>

}