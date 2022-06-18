package com.kanban.board

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KanbanBoardApplication

fun main(args: Array<String>) {
	runApplication<KanbanBoardApplication>(*args)
}
