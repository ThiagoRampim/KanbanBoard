package com.kanban.board.adapter.service.board

import com.kanban.board.domain.core.model.entity.board.BoardColumn
import com.kanban.board.domain.core.model.entity.board.Card
import com.kanban.board.domain.core.model.extension.board.toCardDetailsResponse
import com.kanban.board.domain.core.model.extension.board.toSaveCardRespose
import com.kanban.board.domain.core.model.extension.board.toSimpleCardResponse
import com.kanban.board.domain.core.model.request.board.AddCardRequest
import com.kanban.board.domain.core.model.request.board.UpdateCardRequest
import com.kanban.board.domain.core.model.response.board.SaveCardRespose
import com.kanban.board.domain.core.model.response.boardColumn.CardDetailsResponse
import com.kanban.board.domain.core.model.response.boardColumn.SimpleCardResponse
import com.kanban.board.domain.port.repository.board.BoardColumnRepository
import com.kanban.board.domain.port.repository.board.CardRepository
import com.kanban.board.domain.port.service.board.BoardColumnService
import com.kanban.board.shared.exception.BadRequestException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class BoardColumnServiceImpl(
    val boardColumnRepository: BoardColumnRepository,
    val cardRepository: CardRepository
): BoardColumnService {

    override fun addCard(boardId: UUID, columnId: UUID, addCardRequest: AddCardRequest): SaveCardRespose {
        val boardColumn = findBoardColumnByIdAndBoardIdOrElseThrow(columnId, boardId)
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
        columnId: UUID,
        cardId: UUID,
        updateCardRequest: UpdateCardRequest
    ): SaveCardRespose {
        val card = findByIdAndBoardColumnIdAndBoardIdOrElseThrow(cardId, columnId, boardId)
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

    override fun findCardsByColumn(boardId: UUID, columnId: UUID, pageable: Pageable): Page<SimpleCardResponse> {
        return cardRepository.findAllByBoardColumnIdAndColumnId(columnId, boardId, pageable)
            .map { it.toSimpleCardResponse() }
    }

    override fun findCardDetails(boardId: UUID, columnId: UUID, cardId: UUID): CardDetailsResponse {
        return findByIdAndBoardColumnIdAndBoardIdOrElseThrow(cardId, columnId, boardId).toCardDetailsResponse()
    }

    private fun findByIdAndBoardColumnIdAndBoardIdOrElseThrow(cardId: UUID, boardColumnId: UUID, boardId: UUID): Card {
        return findByIdAndBoardColumnIdAndBoardId(cardId, boardColumnId, boardId)
            ?: throw BadRequestException("Cartão não encontrado")
    }

    private fun findByIdAndBoardColumnIdAndBoardId(cardId: UUID, boardColumnId: UUID, boardId: UUID): Card? {
        return cardRepository.findByIdAndBoardColumnIdAndBoardId(cardId, boardColumnId, boardId)
    }

    private fun findBoardColumnByIdAndBoardIdOrElseThrow(boardColumnId: UUID, boardId: UUID): BoardColumn {
        return findBoardColumnByIdAndBoardId(boardColumnId, boardId)
            ?: throw BadRequestException("Coluna não encontrada")
    }

    private fun findBoardColumnByIdAndBoardId(boardColumnId: UUID, boardId: UUID): BoardColumn? {
        return boardColumnRepository.findByIdAndBoardId(boardColumnId, boardId)
    }

}