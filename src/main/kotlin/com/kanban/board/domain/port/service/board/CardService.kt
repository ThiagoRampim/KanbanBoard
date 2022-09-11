package com.kanban.board.domain.port.service.board

import com.kanban.board.domain.core.model.request.board.AddCardRequest
import com.kanban.board.domain.core.model.request.board.UpdateCardRequest
import com.kanban.board.domain.core.model.response.board.SaveCardRespose
import com.kanban.board.domain.core.model.response.boardColumn.CardDetailsResponse
import com.kanban.board.domain.core.model.response.boardColumn.SimpleCardResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface CardService {

    fun createCard(boardId: UUID, columnId: UUID, addCardRequest: AddCardRequest): SaveCardRespose

    fun updateCard(boardId: UUID, columnId: UUID, cardId: UUID, updateCardRequest: UpdateCardRequest): SaveCardRespose

    fun findCardsByColumn(boardId: UUID, columnId: UUID, pageable: Pageable):Page<SimpleCardResponse>

    fun findCardDetails(boardId: UUID, columnId: UUID, cardId: UUID): CardDetailsResponse

}