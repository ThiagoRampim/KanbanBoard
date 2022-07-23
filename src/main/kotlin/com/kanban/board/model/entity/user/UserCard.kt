package com.kanban.board.model.entity.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kanban.board.model.entity.board.Card
import org.springframework.data.annotation.Id
import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "user", name = "user_card")
data class UserCard(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    var user: User,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false, referencedColumnName = "id")
    var card: Card
)
