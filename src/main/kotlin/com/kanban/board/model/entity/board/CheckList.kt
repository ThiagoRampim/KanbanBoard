package com.kanban.board.model.entity.board

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.Id
import java.time.OffsetDateTime
import java.time.OffsetDateTime.now
import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "board", name = "check_list")
data class CheckList(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "title", length = 150, nullable = false)
    var type: String,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false, referencedColumnName = "id")
    var card: Card,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = now()
)
