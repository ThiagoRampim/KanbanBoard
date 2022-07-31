package com.kanban.board.domain.port.repository

import com.kanban.board.domain.core.model.entity.board.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BoardRepository : JpaRepository<Board, UUID> {}