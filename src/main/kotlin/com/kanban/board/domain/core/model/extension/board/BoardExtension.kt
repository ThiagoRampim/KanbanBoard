package com.kanban.board.domain.core.model.extension.board

import com.kanban.board.domain.core.model.entity.board.Board
import com.kanban.board.domain.core.model.extension.user.toSimpleUserResponse
import com.kanban.board.domain.core.model.response.board.SavedBoardResponse
import com.kanban.board.domain.core.model.response.board.SimpleBoardResponse

fun Board.toSavedBoardResponse() =
    SavedBoardResponse(
        id = this.id,
        name = this.name,
        createdAt = this.createdAt,
        columns = this.columns.map { it.toSavedBoardColumnResponse() }.sortedBy { it.position },
        tags = this.tags.map { it.toSavedTagResponse() },
        members = this.userRelations.map { it.user.toSimpleUserResponse() }
    )

fun Board.toSimpleBoardResponse() =
    SimpleBoardResponse(
        id = this.id,
        name = this.name
    )