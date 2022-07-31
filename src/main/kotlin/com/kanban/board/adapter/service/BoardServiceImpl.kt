package com.kanban.board.adapter.service

import com.kanban.board.domain.core.model.entity.board.Board
import com.kanban.board.domain.core.model.extension.toSavedBoardResponse
import com.kanban.board.domain.core.model.request.CreateBoardRequest
import com.kanban.board.domain.core.model.request.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.SavedBoardResponse
import com.kanban.board.domain.port.repository.BoardRepository
import com.kanban.board.domain.port.service.BoardService
import com.kanban.board.shared.exception.BadRequestException
import org.springframework.stereotype.Service
import java.util.*

@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository
) : BoardService {

    override fun createBoard(createBoardRequest: CreateBoardRequest): SavedBoardResponse {
        val savedBoard = boardRepository.save(
            Board(name = createBoardRequest.name)
        )
        return savedBoard.toSavedBoardResponse()
    }

    override fun updateBoard(boardId: UUID, updateBoardRequest: UpdateBoardRequest): SavedBoardResponse {
        val board = findBoardByIdOrElseThrow(boardId)

        board.apply {
            this.name = updateBoardRequest.name
        }

        val savedBoard = boardRepository.save(board)
        return savedBoard.toSavedBoardResponse()
    }

    override fun findBoard(boardId: UUID): SavedBoardResponse {
        return findBoardByIdOrElseThrow(boardId).toSavedBoardResponse()
    }

    private fun findBoardByIdOrElseThrow(boardId: UUID): Board {
        return findBoardById(boardId)
            ?: throw BadRequestException("Quadro não encontrado")
    }

    private fun findBoardById(boardId: UUID): Board? {
        return boardRepository.findById(boardId).orElse(null)
    }

}