package com.kanban.board.adapter.rest

import com.kanban.board.domain.core.model.request.board.CreateBoardRequest
import com.kanban.board.domain.core.model.request.board.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.board.SavedBoardResponse
import com.kanban.board.domain.port.rest.BoardController
import com.kanban.board.domain.port.service.BoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class BoardControllerImpl(
    private val boardService: BoardService
) : BoardController {

    override fun createBoard(createBoardRequest: CreateBoardRequest): ResponseEntity<SavedBoardResponse> {
        return ResponseEntity.ok(boardService.createBoard(createBoardRequest))
    }

    override fun updateBoard(
        boardId: UUID,
        updateBoardRequest: UpdateBoardRequest
    ): ResponseEntity<SavedBoardResponse> {
        return ResponseEntity.ok(boardService.updateBoard(boardId, updateBoardRequest))
    }

    override fun findBoard(boardId: UUID): ResponseEntity<SavedBoardResponse> {
        return ResponseEntity.ok(boardService.findBoard(boardId))
    }

}