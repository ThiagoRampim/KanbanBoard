package com.kanban.board.domain.port.service.board

import com.kanban.board.domain.core.model.request.board.*
import com.kanban.board.domain.core.model.response.board.SaveCardResponse
import com.kanban.board.domain.core.model.response.boardColumn.CardDetailsResponse
import com.kanban.board.domain.core.model.response.boardColumn.SimpleCardResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface CardService {

    fun createCard(boardId: UUID, columnId: UUID, addCardRequest: AddCardRequest): SaveCardResponse

    fun updateCard(boardId: UUID, columnId: UUID, cardId: UUID, updateCardRequest: UpdateCardRequest): SaveCardResponse

    fun moveCardTo(boardId: UUID, columnId: UUID, cardId: UUID, moveCardToRequest: MoveCardToRequest): SaveCardResponse

    fun updateCardTags(
        boardId: UUID,
        columnId: UUID,
        cardId: UUID,
        updateCardTagsRequest: UpdateCardTagsRequest
    ): SaveCardResponse

    fun updateCardParticipants(
        boardId: UUID,
        columnId: UUID,
        cardId: UUID,
        updateCardParticipantsRequest: UpdateCardParticipantsRequest
    ): SaveCardResponse

    fun findCardsByColumn(boardId: UUID, columnId: UUID, pageable: Pageable):Page<SimpleCardResponse>

    fun findCardDetails(boardId: UUID, columnId: UUID, cardId: UUID): CardDetailsResponse

}