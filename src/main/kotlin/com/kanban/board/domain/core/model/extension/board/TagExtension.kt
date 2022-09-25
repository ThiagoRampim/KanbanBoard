package com.kanban.board.domain.core.model.extension.board

import com.kanban.board.domain.core.model.entity.board.Tag
import com.kanban.board.domain.core.model.response.board.SavedTagResponse

fun Tag.toSavedTagResponse(): SavedTagResponse {
    return SavedTagResponse(
        id = this.id,
        title = this.title,
        type = this.type,
        color = this.color,
        boardId = this.board.id,
        createdAt = this.createdAt
    )
}