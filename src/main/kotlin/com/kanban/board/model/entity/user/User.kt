package com.kanban.board.model.entity.user

import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import java.time.OffsetDateTime.now
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "user", name = "user")
data class User(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "first_name", length = 250, nullable = false)
    var firstName: String,

    @Column(name = "last_name", length = 250, nullable = true)
    var lastName: String? = null,

    @Column(name = "email", length = 320, nullable = false, unique = true)
    var email: String,

    @Column(name = "photo_url", length = 2048, nullable = true)
    var photoUrl: String? = null,

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = now()
)
