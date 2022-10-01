package com.kanban.board.domain.core.model.extension.board

import com.kanban.board.domain.core.model.entity.board.Card
import com.kanban.board.domain.core.model.extension.user.toSimpleUserResponse
import com.kanban.board.domain.core.model.response.board.SaveCardResponse
import com.kanban.board.domain.core.model.response.boardColumn.CardDetailsResponse
import com.kanban.board.domain.core.model.response.boardColumn.SimpleCardResponse

fun Card.toSaveCardRespose() =
    SaveCardResponse(
        id = this.id,
        title = this.title,
        description = this.description,
        boardId = this.boardColumn.board.id,
        columnId = this.boardColumn.id,
        startDate = this.startDate,
        endDate = this.endDate,
        concludedAt = this.concludedAt,
        createdAt = this.createdAt,
        tags = this.cardTag.map { it.tag.toSimpleTagResponse() },
        participants = this.cardUser.map { it.user.toSimpleUserResponse() }
    )

fun Card.toSimpleCardResponse() =
    SimpleCardResponse(
        id = this.id,
        title = this.title
    )

fun Card.toCardDetailsResponse() =
    CardDetailsResponse(
        id = this.id,
        title = this.title,
        description = this.description,
        startDate = this.startDate,
        endDate = this.endDate,
        concludedAt = this.concludedAt,
        createdAt = this.createdAt
    )