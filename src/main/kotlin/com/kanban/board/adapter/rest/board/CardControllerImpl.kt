package com.kanban.board.adapter.rest.board

import com.kanban.board.domain.core.model.request.board.AddCardRequest
import com.kanban.board.domain.core.model.request.board.UpdateCardRequest
import com.kanban.board.domain.core.model.request.board.UpdateCardTagsRequest
import com.kanban.board.domain.core.model.response.board.SaveCardResponse
import com.kanban.board.domain.core.model.response.boardColumn.CardDetailsResponse
import com.kanban.board.domain.core.model.response.boardColumn.SimpleCardResponse
import com.kanban.board.domain.port.rest.board.CardController
import com.kanban.board.domain.port.service.board.CardService
import com.kanban.board.domain.port.service.user.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Card", description = "Provides card apis")
class CardControllerImpl(
    val cardService: CardService,
    private val userService: UserService
): CardController {

    override fun createCard(
        boardId: UUID,
        columnId: UUID,
        addCardRequest: AddCardRequest
    ): ResponseEntity<SaveCardResponse> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(cardService.createCard(boardId, columnId, addCardRequest))
    }

    override fun updateCard(
        boardId: UUID,
        columnId: UUID,
        cardId: UUID,
        updateCardRequest: UpdateCardRequest
    ): ResponseEntity<SaveCardResponse> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(cardService.updateCard(boardId, columnId, cardId, updateCardRequest))
    }

    override fun updateCardTags(
        boardId: UUID,
        columnId: UUID,
        cardId: UUID,
        updateCardTagsRequest: UpdateCardTagsRequest
    ): ResponseEntity<SaveCardResponse> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(cardService.updateCardTags(boardId, columnId, cardId, updateCardTagsRequest))
    }

    override fun findCardsByColumn(
        boardId: UUID,
        columnId: UUID,
        pageable: Pageable
    ): ResponseEntity<Page<SimpleCardResponse>> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(cardService.findCardsByColumn(boardId, columnId, pageable))
    }

    override fun findCardDetails(boardId: UUID, columnId: UUID, cardId: UUID): ResponseEntity<CardDetailsResponse> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(cardService.findCardDetails(boardId, columnId, cardId))
    }

}