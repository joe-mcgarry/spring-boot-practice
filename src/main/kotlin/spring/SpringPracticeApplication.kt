package spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping

@SpringBootApplication
class SpringPracticeApplication

fun main(args: Array<String>) {
	runApplication<SpringPracticeApplication>(*args)
}

@RestController
class MessageResource(val service: MessageService) {
	@GetMapping
	fun index(): List<Message> = service.findMessages()

	@PostMapping
	fun post(@RequestBody message: Message) {
		service.post(message)
	}
}
@Table("messages")
data class Message(@Id val id: String?, val text: String)

interface MessageRepository : CrudRepository<Message, String> {
	@Query("select * from messages")
	fun findMessages(): List<Message>
}

@Service
class MessageService(val db: MessageRepository) {
	fun findMessages(): List<Message> = db.findMessages()

	fun post(message: Message) {
		db.save(message)
	}
}