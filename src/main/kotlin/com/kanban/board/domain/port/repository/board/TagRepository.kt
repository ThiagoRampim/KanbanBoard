package com.kanban.board.domain.port.repository.board

import com.kanban.board.domain.core.model.entity.board.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TagRepository : JpaRepository<Tag, UUID> {

    fun findAllByBoardId(boardId: UUID): List<Tag>

}