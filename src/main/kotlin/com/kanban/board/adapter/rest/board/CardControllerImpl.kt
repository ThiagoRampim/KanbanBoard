package com.kanban.board.adapter.rest.board

import com.kanban.board.domain.core.model.request.board.AddCardRequest
import com.kanban.board.domain.core.model.request.board.UpdateCardRequest
import com.kanban.board.domain.core.model.response.board.SaveCardRespose
import com.kanban.board.domain.port.rest.board.CardController
import com.kanban.board.domain.port.service.board.BoardColumnService
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Card", description = "Provides card apis")
class CardControllerImpl(
    val boardColumnService: BoardColumnService
): CardController {

    override fun addCard(
        boardId: UUID,
        addCardRequest: AddCardRequest
    ): ResponseEntity<SaveCardRespose> {
        return ResponseEntity.ok(boardColumnService.addCard(boardId, addCardRequest))
    }

    override fun updateCard(
        boardId: UUID,
        cardId: UUID,
        updateCardRequest: UpdateCardRequest
    ): ResponseEntity<SaveCardRespose> {
        return ResponseEntity.ok(boardColumnService.updateCard(boardId, cardId, updateCardRequest))
    }

}