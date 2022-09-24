package com.kanban.board.domain.port.service.board

import com.kanban.board.domain.core.model.request.board.CreateTagRequest
import com.kanban.board.domain.core.model.response.board.SaveTagResponse
import java.util.*

interface TagService {

    fun createTag(boardId: UUID, createTagRequest: CreateTagRequest): SaveTagResponse

    fun listTags(boardId: UUID): List<SaveTagResponse>

}