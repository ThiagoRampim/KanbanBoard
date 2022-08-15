package com.kanban.board.domain.port.rest.board

import com.kanban.board.domain.core.model.request.board.AddCardRequest
import com.kanban.board.domain.core.model.request.board.UpdateCardRequest
import com.kanban.board.domain.core.model.response.board.SaveCardRespose
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/v1/card")
interface CardController {

    @PostMapping
    fun addCard(
        @RequestHeader("Board-Id") boardId: UUID,
        @RequestBody addCardRequest: AddCardRequest
    ): ResponseEntity<SaveCardRespose>

    @PutMapping("{cardId}")
    fun updateCard(
        @RequestHeader("Board-Id") boardId: UUID,
        @PathVariable("cardId") cardId: UUID,
        @RequestBody updateCardRequest: UpdateCardRequest
    ): ResponseEntity<SaveCardRespose>

}