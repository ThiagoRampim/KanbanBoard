package com.kanban.board.model.entity.board

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.Id
import java.time.OffsetDateTime
import java.time.OffsetDateTime.now
import java.util.*
import javax.persistence.*
import javax.persistence.Column

@Entity
@Table(schema = "board", name = "board_column")
data class BoardColumn(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "name", length = 150, nullable = false)
    var name: String,

    @Column(name = "position", nullable = false)
    var position: Int,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false, referencedColumnName = "id")
    var board: Board,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = now()
)
