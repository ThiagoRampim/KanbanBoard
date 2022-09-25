package com.kanban.board.adapter.rest.board

import com.kanban.board.domain.core.model.request.board.CreateTagRequest
import com.kanban.board.domain.core.model.response.board.SavedTagResponse
import com.kanban.board.domain.port.rest.board.TagController
import com.kanban.board.domain.port.service.board.TagService
import com.kanban.board.domain.port.service.user.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@Tag(name = "Tag", description = "Provides tag apis")
class TagControllerImpl(
    private val tagService: TagService,
    private val userService: UserService
) : TagController {

    override fun createTag(
        boardId: UUID,
        createTagRequest: CreateTagRequest
    ): ResponseEntity<SavedTagResponse> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(tagService.createTag(boardId, createTagRequest))
    }

    override fun listTags(boardId: UUID): ResponseEntity<List<SavedTagResponse>> {
        userService.blockIfCurrentUserHasNotAccessToBoard(boardId)
        return ResponseEntity.ok(tagService.listTags(boardId))
    }

}