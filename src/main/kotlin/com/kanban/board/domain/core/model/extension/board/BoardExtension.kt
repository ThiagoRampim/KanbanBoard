package com.kanban.board.domain.core.model.extension

import com.kanban.board.domain.core.model.entity.board.Board
import com.kanban.board.domain.core.model.extension.board.toSavedBoardColumnResponse
import com.kanban.board.domain.core.model.response.board.SavedBoardResponse

fun Board.toSavedBoardResponse() =
    SavedBoardResponse(
        id = this.id,
        name = this.name,
        columns = this.columns.map { it.toSavedBoardColumnResponse() }.sortedBy { it.position },
        createdAt = this.createdAt
    )