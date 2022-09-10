package com.kanban.board.adapter.rest.board

import com.kanban.board.domain.core.model.request.board.AddCardRequest
import com.kanban.board.domain.core.model.request.board.UpdateCardRequest
import com.kanban.board.domain.core.model.response.board.SaveCardRespose
import com.kanban.board.domain.core.model.response.boardColumn.CardDetailsResponse
import com.kanban.board.domain.core.model.response.boardColumn.SimpleCardResponse
import com.kanban.board.domain.port.rest.board.BoardColumnController
import com.kanban.board.domain.port.service.board.BoardColumnService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Card", description = "Provides card apis")
class BoardColumnControllerImpl(
    val boardColumnService: BoardColumnService
): BoardColumnController {

    override fun addCard(
        boardId: UUID,
        columnId: UUID,
        addCardRequest: AddCardRequest
    ): ResponseEntity<SaveCardRespose> {
        return ResponseEntity.ok(boardColumnService.addCard(boardId, columnId, addCardRequest))
    }

    override fun updateCard(
        boardId: UUID,
        columnId: UUID,
        cardId: UUID,
        updateCardRequest: UpdateCardRequest
    ): ResponseEntity<SaveCardRespose> {
        return ResponseEntity.ok(boardColumnService.updateCard(boardId, columnId, cardId, updateCardRequest))
    }

    override fun findCardsByColumn(
        boardId: UUID,
        columnId: UUID,
        pageable: Pageable
    ): ResponseEntity<Page<SimpleCardResponse>> {
        return ResponseEntity.ok(boardColumnService.findCardsByColumn(boardId, columnId, pageable))
    }

    override fun findCardDetails(boardId: UUID, columnId: UUID, cardId: UUID): ResponseEntity<CardDetailsResponse> {
        return ResponseEntity.ok(boardColumnService.findCardDetails(boardId, columnId, cardId))
    }

}