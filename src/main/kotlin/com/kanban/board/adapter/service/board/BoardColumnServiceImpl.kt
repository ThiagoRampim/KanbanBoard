package com.kanban.board.adapter.service.board

import com.kanban.board.domain.core.model.entity.board.BoardColumn
import com.kanban.board.domain.core.model.entity.board.Card
import com.kanban.board.domain.core.model.extension.board.toSaveCardRespose
import com.kanban.board.domain.core.model.request.board.AddCardRequest
import com.kanban.board.domain.core.model.request.board.UpdateCardRequest
import com.kanban.board.domain.core.model.response.board.SaveCardRespose
import com.kanban.board.domain.port.repository.board.BoardColumnRepository
import com.kanban.board.domain.port.repository.board.CardRepository
import com.kanban.board.domain.port.service.board.BoardColumnService
import com.kanban.board.shared.exception.BadRequestException
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class BoardColumnServiceImpl(
    val boardColumnRepository: BoardColumnRepository,
    val cardRepository: CardRepository
): BoardColumnService {

    override fun addCard(
        boardId: UUID,
        addCardRequest: AddCardRequest
    ): SaveCardRespose {
        val boardColumn = findBoardColumnByIdAndBoardIdOrElseThrow(addCardRequest.columnId, boardId)
        val card = Card(
            title = addCardRequest.title,
            description = addCardRequest.description,
            boardColumn = boardColumn,
            startDate = addCardRequest.startDate,
            endDate = addCardRequest.endDate,
            concludedAt = addCardRequest.concludedAt
        )

        return cardRepository.save(card).toSaveCardRespose()
    }

    override fun updateCard(
        boardId: UUID,
        cardId: UUID,
        updateCardRequest: UpdateCardRequest
    ): SaveCardRespose {
        val card = findCardByIdAndBoardIdOrElseThrow(cardId, boardId)
        card.apply {
            this.title = updateCardRequest.title
            this.description = updateCardRequest.description
            this.startDate = updateCardRequest.startDate
            this.endDate = updateCardRequest.endDate
            this.concludedAt = updateCardRequest.concludedAt

            if (this.boardColumn.id != updateCardRequest.columnId) {
                this.boardColumn = findBoardColumnByIdAndBoardIdOrElseThrow(updateCardRequest.columnId, boardId)
            }
        }

        return cardRepository.save(card).toSaveCardRespose()
    }

    private fun findCardByIdAndBoardIdOrElseThrow(
        cardId: UUID,
        boardId: UUID
    ): Card {
        return findCardByIdAndBoardId(cardId, boardId)
            ?: throw BadRequestException("Cartão não encontrado")
    }

    private fun findCardByIdAndBoardId(
        cardId: UUID,
        boardId: UUID
    ): Card? {
        return cardRepository.findByIdAndBoardId(cardId, boardId)
    }

    private fun findBoardColumnByIdAndBoardIdOrElseThrow(
        boardColumnId: UUID,
        boardId: UUID
    ): BoardColumn {
        return findBoardColumnByIdAndBoardId(boardColumnId, boardId)
            ?: throw BadRequestException("Coluna não encontrada")
    }

    private fun findBoardColumnByIdAndBoardId(
        boardColumnId: UUID,
        boardId: UUID
    ): BoardColumn? {
        return boardColumnRepository.findByIdAndBoardId(boardColumnId, boardId)
    }

}