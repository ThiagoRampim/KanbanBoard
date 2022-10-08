package com.kanban.board.domain.core.model.entity.user

import com.kanban.board.domain.enums.user.TokenTypeEnum
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.*

@Entity
@Table(schema = "user", name = "token")
data class Token(
    @Id
    @Column(name = "id", nullable = false)
    val id: UUID = UUID.randomUUID(),

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", length = 30, nullable = false)
    val type: TokenTypeEnum,

    @Column(name = "token", nullable = false)
    val token: String,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @Column(name = "valid_until", nullable = false)
    val validUntil: OffsetDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    val user: User
)