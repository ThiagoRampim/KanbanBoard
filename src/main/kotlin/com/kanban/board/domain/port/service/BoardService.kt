package com.kanban.board.domain.port.service

import com.kanban.board.domain.core.model.request.CreateBoardRequest
import com.kanban.board.domain.core.model.request.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.SavedBoardResponse
import java.util.*

interface BoardService {

    fun createBoard(createBoardRequest: CreateBoardRequest): SavedBoardResponse

    fun updateBoard(boardId: UUID, updateBoardRequest: UpdateBoardRequest): SavedBoardResponse

    fun findBoard(boardId: UUID): SavedBoardResponse

}