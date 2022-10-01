package com.kanban.board.domain.core.model.extension.board

import com.kanban.board.domain.core.model.entity.board.Tag
import com.kanban.board.domain.core.model.response.board.SavedTagResponse
import com.kanban.board.domain.core.model.response.board.SimpleTagResponse

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

fun Tag.toSimpleTagResponse(): SimpleTagResponse {
    return SimpleTagResponse(
        id = this.id,
        title = this.title,
        type = this.type,
        color = this.color
    )
}