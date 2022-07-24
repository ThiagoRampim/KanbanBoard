package com.kanban.board.domain.core.model.entity.board

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import java.time.OffsetDateTime.now
import java.util.*
import javax.persistence.*

@Entity
@Table(schema = "board", name = "check_list_item")
data class CheckListItem(
    @Id
    @Column(name = "id")
    var id: UUID = UUID.randomUUID(),

    @Column(name = "description", length = 350, nullable = false)
    var description: String,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_list_id", nullable = false, referencedColumnName = "id")
    var checkList: CheckList,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = now()
)
