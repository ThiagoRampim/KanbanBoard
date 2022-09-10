package com.kanban.board.domain.port.service.board

import com.kanban.board.domain.core.model.entity.board.Board
import com.kanban.board.domain.core.model.request.board.CreateBoardRequest
import com.kanban.board.domain.core.model.request.board.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.board.SavedBoardResponse
import java.util.*

interface BoardService {

    fun createBoard(createBoardRequest: CreateBoardRequest): SavedBoardResponse

    fun updateBoard(boardId: UUID, updateBoardRequest: UpdateBoardRequest): SavedBoardResponse

    fun findBoard(boardId: UUID): SavedBoardResponse

    fun findBoardByIdOrElseThrow(boardId: UUID): Board

    fun findBoardById(boardId: UUID): Board?

}