package com.kanban.board.adapter.rest.board

import com.kanban.board.domain.core.model.request.board.CreateTagRequest
import com.kanban.board.domain.core.model.response.board.SaveTagResponse
import com.kanban.board.domain.port.rest.board.TagController
import com.kanban.board.domain.port.service.board.TagService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@Tag(name = "Tag", description = "Provides tag apis")
class TagControllerImpl(
    private val tagService: TagService
) : TagController {

    override fun createTag(
        boardId: UUID,
        createTagRequest: CreateTagRequest
    ): ResponseEntity<SaveTagResponse> {
        return ResponseEntity.ok(tagService.createTag(boardId, createTagRequest))
    }

    override fun listTags(boardId: UUID): ResponseEntity<List<SaveTagResponse>> {
        return ResponseEntity.ok(tagService.listTags(boardId))
    }

}