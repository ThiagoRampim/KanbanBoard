package com.kanban.board.adapter.service.board

import com.kanban.board.domain.core.model.entity.board.Board
import com.kanban.board.domain.core.model.entity.board.BoardColumn
import com.kanban.board.domain.core.model.entity.board.Tag
import com.kanban.board.domain.core.model.entity.user.User
import com.kanban.board.domain.core.model.entity.user.UserBoard
import com.kanban.board.domain.core.model.extension.board.toSavedBoardResponse
import com.kanban.board.domain.core.model.request.board.CreateBoardRequest
import com.kanban.board.domain.core.model.request.board.UpdateBoardRequest
import com.kanban.board.domain.core.model.response.board.SavedBoardResponse
import com.kanban.board.domain.enums.TagTypeEnum
import com.kanban.board.domain.port.repository.board.BoardRepository
import com.kanban.board.domain.port.service.board.BoardService
import com.kanban.board.shared.exception.BadRequestException
import org.springframework.stereotype.Service
import java.util.*

@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository
) : BoardService {

    override fun createBoard(createBoardRequest: CreateBoardRequest, actorUser: User): SavedBoardResponse {
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
            this.tags.addAll(
                listOf(
                    Tag(title = "Alta", type = TagTypeEnum.PRIORITY, color = "#FF1F1F", board = this),
                    Tag(title = "Média", type = TagTypeEnum.PRIORITY, color = "#FFEF1F", board = this),
                    Tag(title = "Baixa", type = TagTypeEnum.PRIORITY, color = "#79FF1F", board = this)
                )
            )
            this.userRelations.add(
                UserBoard(user = actorUser, board = this)
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

    override fun findBoardByIdOrElseThrow(boardId: UUID): Board {
        return findBoardById(boardId)
            ?: throw BadRequestException("Quadro não encontrado")
    }

    override fun findBoardById(boardId: UUID): Board? {
        return boardRepository.findByIdFetched(boardId)
    }

}