package com.kanban.board.domain.core.model.request.board

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class UpdateCardParticipantsRequest(
    @JsonProperty("add_participants")
    val addParticipants: List<UUID>,

    @JsonProperty("remove_participants")
    val removeParticipants: List<UUID>,
)
