package com.kanban.board.domain.core.model.entity.board

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "board", name = "card_tag")
data class CardTag(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false, referencedColumnName = "id")
    var card: Card,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false, referencedColumnName = "id")
    var tag: Tag
)
