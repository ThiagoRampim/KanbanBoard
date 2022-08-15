package com.kanban.board.domain.core.model.extension.board

import com.kanban.board.domain.core.model.entity.board.Card
import com.kanban.board.domain.core.model.response.board.SaveCardRespose

fun Card.toSaveCardRespose() =
    SaveCardRespose(
        id = this.id,
        title = this.title,
        description = this.description,
        boardId = this.boardColumn.board.id,
        columnId = this.boardColumn.id,
        startDate = this.startDate,
        endDate = this.endDate,
        concludedAt = this.concludedAt,
        createdAt = this.createdAt
    )