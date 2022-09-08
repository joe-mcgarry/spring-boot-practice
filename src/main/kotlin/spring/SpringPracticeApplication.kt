package spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@SpringBootApplication
class SpringPracticeApplication

fun main(args: Array<String>) {
	runApplication<SpringPracticeApplication>(*args)
}

@RestController
class MessageResource {
	@GetMapping
	fun index(): List<Message> = listOf(
		Message("1", "Hello"),
		Message("2", "Bonjour"),
		Message("3", "Privet!"),
	)
}
@Table("messages")
data class Message(@Id val id: String?, val text: String)


