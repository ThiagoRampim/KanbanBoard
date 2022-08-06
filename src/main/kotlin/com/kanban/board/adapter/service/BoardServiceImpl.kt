package com.kanban.board.adapter.service

import com.kanban.board.domain.core.model.entity.board.Board
import com.kanban.board.domain.core.model.entity.board.BoardColumn
import com.kanban.board.domain.core.model.extension.toSavedBoardResponse
import com.kanban.board.domain.core.model.request.board.CreateBoardRequest
import com.kanban.board.domain.core.model.request.board.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.board.SavedBoardResponse
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
        val board = Board(name = createBoardRequest.name)
        board.apply {
            this.columns.addAll(
                listOf(
                    BoardColumn(name = "Backlog", position = 0, board = this),
                    BoardColumn(name = "To-do", position = 1, board = this),
                    BoardColumn(name = "Doing", position = 2, board = this),
                    BoardColumn(name = "Review", position = 3, board = this),
                    BoardColumn(name = "Done", position = 4, board = this)
                )
            )
        }

        return boardRepository.save(board).toSavedBoardResponse()
    }

    override fun updateBoard(boardId: UUID, updateBoardRequest: UpdateBoardRequest): SavedBoardResponse {
        val board = findBoardByIdOrElseThrow(boardId)
        board.apply {
            this.name = updateBoardRequest.name
        }

        return boardRepository.save(board).toSavedBoardResponse()
    }

    override fun findBoard(boardId: UUID): SavedBoardResponse {
        return findBoardByIdOrElseThrow(boardId).toSavedBoardResponse()
    }

    private fun findBoardByIdOrElseThrow(boardId: UUID): Board {
        return findBoardById(boardId)
            ?: throw BadRequestException("Quadro não encontrado")
    }

    private fun findBoardById(boardId: UUID): Board? {
        return boardRepository.findByIdFetched(boardId)
    }

}