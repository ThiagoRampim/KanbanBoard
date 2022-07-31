package com.kanban.board.domain.port.rest

import com.kanban.board.domain.core.model.request.CreateBoardRequest
import com.kanban.board.domain.core.model.request.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.SavedBoardResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@RequestMapping("/api/v1/board")
interface BoardController {

    @PostMapping
    fun createBoard(
        @RequestBody createBoardRequest: CreateBoardRequest
    ): ResponseEntity<SavedBoardResponse>

    @PutMapping("/{boardId}")
    fun updateBoard(
        @PathVariable boardId: UUID,
        @RequestBody updateBoardRequest: UpdateBoardRequest
    ): ResponseEntity<SavedBoardResponse>

    @GetMapping("/{boardId}")
    fun findBoard(
        @PathVariable boardId: UUID
    ): ResponseEntity<SavedBoardResponse>

}