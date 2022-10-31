package com.kanban.board.domain.port.rest.board

import com.kanban.board.domain.core.model.request.board.CreateBoardRequest
import com.kanban.board.domain.core.model.request.board.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.board.SavedBoardResponse
import com.kanban.board.domain.core.model.response.board.SimpleBoardResponse
import com.kanban.board.domain.core.model.response.user.SimpleUserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
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

    @PutMapping("/{boardId}/add-member/{userEmail}")
    fun addMemberToBoard(
        @PathVariable boardId: UUID,
        @PathVariable userEmail: String,
    ): ResponseEntity<SavedBoardResponse>

    @DeleteMapping("/{boardId}/remove-member/{userId}")
    fun removeMemberToBoard(
        @PathVariable boardId: UUID,
        @PathVariable userId: UUID,
    ): ResponseEntity<SavedBoardResponse>

    @GetMapping
    fun findBoard(
        pageable: Pageable
    ): ResponseEntity<Page<SimpleBoardResponse>>

    @GetMapping("/{boardId}")
    fun findBoard(
        @PathVariable boardId: UUID
    ): ResponseEntity<SavedBoardResponse>

    @GetMapping("/{boardId}/members")
    fun findBoardMembers(
        @PathVariable boardId: UUID
    ): ResponseEntity<List<SimpleUserResponse>>

}