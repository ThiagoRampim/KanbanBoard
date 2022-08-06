package com.kanban.board.domain.port.service

import com.kanban.board.domain.core.model.request.board.CreateBoardRequest
import com.kanban.board.domain.core.model.request.board.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.board.SavedBoardResponse
import java.util.*

interface BoardService {

    fun createBoard(createBoardRequest: CreateBoardRequest): SavedBoardResponse

    fun updateBoard(boardId: UUID, updateBoardRequest: UpdateBoardRequest): SavedBoardResponse

    fun findBoard(boardId: UUID): SavedBoardResponse

}