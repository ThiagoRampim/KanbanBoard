package com.kanban.board.domain.core.model.extension.board

import com.kanban.board.domain.core.model.entity.board.BoardColumn
import com.kanban.board.domain.core.model.response.board.SavedBoardColumnResponse

fun BoardColumn.toSavedBoardColumnResponse() =
    SavedBoardColumnResponse(
        id = this.id,
        name = this.name,
        position = this.position,
        createdAt = this.createdAt
    )