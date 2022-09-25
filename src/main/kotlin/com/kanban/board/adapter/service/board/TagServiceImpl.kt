package com.kanban.board.adapter.service.board

import com.kanban.board.domain.core.model.entity.board.Board
import com.kanban.board.domain.core.model.entity.board.Tag
import com.kanban.board.domain.core.model.extension.board.toSavedTagResponse
import com.kanban.board.domain.core.model.request.board.CreateTagRequest
import com.kanban.board.domain.core.model.response.board.SavedTagResponse
import com.kanban.board.domain.enums.TagTypeEnum
import com.kanban.board.domain.port.repository.board.BoardRepository
import com.kanban.board.domain.port.repository.board.TagRepository
import com.kanban.board.domain.port.service.board.TagService
import com.kanban.board.shared.exception.BadRequestException
import org.springframework.stereotype.Service
import java.util.*

@Service
class TagServiceImpl(
    private val tagRepository: TagRepository,
    private val boardRepository: BoardRepository
) : TagService {

    override fun createTag(boardId: UUID, createTagRequest: CreateTagRequest): SavedTagResponse {
        val board = findBoardByIdOrElseThrow(boardId)
        val tag = Tag(
            title = createTagRequest.title,
            type = TagTypeEnum.DESCRIPTION,
            color = createTagRequest.color,
            board = board
        )
        return tagRepository.save(tag).toSavedTagResponse()
    }

    override fun listTags(boardId: UUID): List<SavedTagResponse> {
        return tagRepository.findAllByBoardId(boardId).map { it.toSavedTagResponse() }
    }

    private fun findBoardByIdOrElseThrow(boardId: UUID): Board {
        return findBoardById(boardId)
            ?: throw BadRequestException("Quadro não encontrado")
    }

    private fun findBoardById(boardId: UUID): Board? {
        return boardRepository.findByIdFetched(boardId)
    }

}