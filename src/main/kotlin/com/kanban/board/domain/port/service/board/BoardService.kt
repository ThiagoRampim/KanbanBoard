package com.kanban.board.domain.port.service.board

import com.kanban.board.domain.core.model.entity.board.Board
import com.kanban.board.domain.core.model.entity.user.User
import com.kanban.board.domain.core.model.request.board.CreateBoardRequest
import com.kanban.board.domain.core.model.request.board.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.board.SavedBoardResponse
import com.kanban.board.domain.core.model.response.board.SimpleBoardResponse
import com.kanban.board.domain.core.model.response.user.SimpleUserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface BoardService {

    fun createBoard(createBoardRequest: CreateBoardRequest, actorUser: User): SavedBoardResponse

    fun updateBoard(boardId: UUID, updateBoardRequest: UpdateBoardRequest): SavedBoardResponse

    fun addMemberToBoard(boardId: UUID, userEmail: String): SavedBoardResponse

    fun removeMemberToBoard(boardId: UUID, userId: UUID): SavedBoardResponse

    fun findBoard(boardId: UUID): SavedBoardResponse

    fun findBoardByUserEmail(userEmail: String, pageable: Pageable): Page<SimpleBoardResponse>

    fun findBoardMembers(boardId: UUID): List<SimpleUserResponse>

    fun findBoardByIdOrElseThrow(boardId: UUID): Board

    fun findBoardById(boardId: UUID): Board?

}