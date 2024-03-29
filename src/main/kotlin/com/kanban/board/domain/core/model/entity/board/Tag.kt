package com.kanban.board.domain.core.model.entity.board

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kanban.board.domain.enums.TagTypeEnum
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import java.time.OffsetDateTime.now
import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "board", name = "tag")
data class Tag(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "title", length = 150, nullable = true)
    var title: String?,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 30, nullable = false)
    var type: TagTypeEnum,

    @Column(name = "color", length = 7, nullable = false)
    var color: String,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false, referencedColumnName = "id")
    var board: Board,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = now()
)
