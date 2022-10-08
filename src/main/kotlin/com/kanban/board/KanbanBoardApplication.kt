package com.kanban.board

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class KanbanBoardApplication

fun main(args: Array<String>) {
	runApplication<KanbanBoardApplication>(*args)
}
