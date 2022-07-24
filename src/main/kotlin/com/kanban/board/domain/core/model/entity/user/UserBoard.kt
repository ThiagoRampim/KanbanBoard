package com.kanban.board.domain.core.model.entity.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kanban.board.domain.core.model.entity.board.Board
import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "user", name = "user_board")
data class UserBoard(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    var user: User,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false, referencedColumnName = "id")
    var board: Board
)
