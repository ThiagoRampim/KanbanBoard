package com.kanban.board.domain.port.service.board

import com.kanban.board.domain.core.model.request.board.AddCardRequest
import com.kanban.board.domain.core.model.request.board.UpdateCardRequest
import com.kanban.board.domain.core.model.response.board.SaveCardRespose
import java.util.UUID

interface BoardColumnService {

    fun addCard(boardId: UUID, addCardRequest: AddCardRequest): SaveCardRespose

    fun updateCard(boardId: UUID, cardId: UUID, updateCardRequest: UpdateCardRequest): SaveCardRespose

}