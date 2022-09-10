package com.kanban.board.domain.port.rest.board

import com.kanban.board.domain.core.model.request.board.AddCardRequest
import com.kanban.board.domain.core.model.request.board.UpdateCardRequest
import com.kanban.board.domain.core.model.response.board.SaveCardRespose
import com.kanban.board.domain.core.model.response.boardColumn.CardDetailsResponse
import com.kanban.board.domain.core.model.response.boardColumn.SimpleCardResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/v1/column/{columnId}")
interface BoardColumnController {

    @PostMapping("/card")
    fun addCard(
        @RequestHeader("Board-Id") boardId: UUID,
        @PathVariable("columnId") columnId: UUID,
        @RequestBody addCardRequest: AddCardRequest
    ): ResponseEntity<SaveCardRespose>

    @PutMapping("/card/{cardId}")
    fun updateCard(
        @RequestHeader("Board-Id") boardId: UUID,
        @PathVariable("columnId") columnId: UUID,
        @PathVariable("cardId") cardId: UUID,
        @RequestBody updateCardRequest: UpdateCardRequest
    ): ResponseEntity<SaveCardRespose>

    @GetMapping("/cards")
    fun findCardsByColumn(
        @RequestHeader("Board-Id") boardId: UUID,
        @PathVariable("columnId") columnId: UUID,
        pageable: Pageable
    ): ResponseEntity<Page<SimpleCardResponse>>

    @GetMapping("/card/{cardId}/details")
    fun findCardDetails(
        @RequestHeader("Board-Id") boardId: UUID,
        @PathVariable("columnId") columnId: UUID,
        @PathVariable("cardId") cardId: UUID,
    ): ResponseEntity<CardDetailsResponse>

}