package com.kanban.board.domain.core.model.extension

import com.kanban.board.domain.core.model.entity.board.Board
import com.kanban.board.domain.core.model.response.SavedBoardResponse

fun Board.toSavedBoardResponse() =
    SavedBoardResponse(
        id = this.id,
        name = this.name,
        createdAt = this.createdAt
    )