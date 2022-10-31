package com.kanban.board.adapter.rest.board

import com.kanban.board.domain.core.model.request.board.CreateBoardRequest
import com.kanban.board.domain.core.model.request.board.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.board.SavedBoardResponse
import com.kanban.board.domain.core.model.response.board.SimpleBoardResponse
import com.kanban.board.domain.core.model.response.user.SimpleUserResponse
import com.kanban.board.domain.port.rest.board.BoardController
import com.kanban.board.domain.port.service.board.BoardService
import com.kanban.board.domain.port.service.user.UserService
import com.kanban.board.infrastructure.extension.currentUserEmailOrElseThrow
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@Tag(name = "Board", description = "Provides board apis")
class BoardControllerImpl(
    private val boardService: BoardService,
    private val userService: UserService
) : BoardController {

    override fun createBoard(createBoardRequest: CreateBoardRequest): ResponseEntity<SavedBoardResponse> {
        val currentUser = userService.currentUserOrElseThrow()
        return ResponseEntity.ok(boardService.createBoard(createBoardRequest, currentUser))
    }

    override fun updateBoard(
        boardId: UUID,
        updateBoardRequest: UpdateBoardRequest
    ): ResponseEntity<SavedBoardResponse> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(boardService.updateBoard(boardId, updateBoardRequest))
    }

    override fun addMemberToBoard(
        boardId: UUID,
        userEmail: String
    ): ResponseEntity<SavedBoardResponse> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(boardService.addMemberToBoard(boardId, userEmail))
    }

    override fun removeMemberToBoard(boardId: UUID, userId: UUID): ResponseEntity<SavedBoardResponse> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(boardService.removeMemberToBoard(boardId, userId))
    }

    override fun findBoard(pageable: Pageable): ResponseEntity<Page<SimpleBoardResponse>> {
        return ResponseEntity.ok(boardService.findBoardByUserEmail(currentUserEmailOrElseThrow(), pageable))
    }

    override fun findBoard(boardId: UUID): ResponseEntity<SavedBoardResponse> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(boardService.findBoard(boardId))
    }

    override fun findBoardMembers(boardId: UUID): ResponseEntity<List<SimpleUserResponse>> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(boardService.findBoardMembers(boardId))
    }

}