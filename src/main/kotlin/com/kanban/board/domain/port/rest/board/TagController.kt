package com.kanban.board.domain.port.rest.board

import com.kanban.board.domain.core.model.request.board.CreateTagRequest
import com.kanban.board.domain.core.model.response.board.SavedTagResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@RequestMapping("/api/v1/tag")
interface TagController {

    @PostMapping
    fun createTag(
        @RequestHeader("Board-Id") boardId: UUID,
        @RequestBody createTagRequest: CreateTagRequest
    ): ResponseEntity<SavedTagResponse>

    @GetMapping
    fun listTags(
        @RequestHeader("Board-Id") boardId: UUID
    ): ResponseEntity<List<SavedTagResponse>>

}