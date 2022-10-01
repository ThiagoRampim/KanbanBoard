package com.kanban.board.domain.core.model.entity.board

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kanban.board.domain.core.model.entity.user.UserCard
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import java.time.OffsetDateTime.now
import java.util.*
import javax.persistence.*
import javax.persistence.Column

@Entity
@Table(schema = "board", name = "card")
data class Card(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "title", length = 150, nullable = false)
    var title: String,

    @Column(name = "description", nullable = true)
    var description: String? = null,

    @JsonIgnore
    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, orphanRemoval = true, cascade = [CascadeType.ALL])
    var cardTag: MutableList<CardTag> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY, orphanRemoval = true, cascade = [CascadeType.ALL])
    var cardUser: MutableList<UserCard> = mutableListOf(),

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_column_id", nullable = false, referencedColumnName = "id")
    var boardColumn: BoardColumn,

    @Column(name = "start_date", nullable = true)
    var startDate: OffsetDateTime? = null,

    @Column(name = "end_date", nullable = true)
    var endDate: OffsetDateTime? = null,

    @Column(name = "concluded_at", nullable = true)
    var concludedAt: OffsetDateTime? = null,

    @Column(name = "order", nullable = false)
    var order: Int,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = now()
)
