package com.kanban.board.domain.core.model.entity.board

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kanban.board.domain.core.model.entity.user.UserBoard
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import java.time.OffsetDateTime.now
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(schema = "board", name = "board")
data class Board(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "name", length = 150, nullable = false)
    var name: String,

    @JsonIgnore
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, orphanRemoval = true, cascade = [CascadeType.ALL])
    var columns: MutableList<BoardColumn> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, orphanRemoval = true, cascade = [CascadeType.ALL])
    var tags: MutableList<Tag> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, orphanRemoval = true, cascade = [CascadeType.ALL])
    var userRelations: MutableList<UserBoard> = mutableListOf(),

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = now()
)
