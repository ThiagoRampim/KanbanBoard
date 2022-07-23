package com.kanban.board.model.entity.board

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kanban.board.model.entity.user.User
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.Id
import java.time.OffsetDateTime
import java.time.OffsetDateTime.now
import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "board", name = "card_history")
data class CardHistory(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "type", length = 50, nullable = false)
    var type: String,

    @Column(name = "content", nullable = true)
    var content: String?,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false, referencedColumnName = "id")
    var card: Card,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    var user: User,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = now()
)
