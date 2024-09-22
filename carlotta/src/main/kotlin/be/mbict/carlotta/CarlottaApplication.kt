package be.mbict.carlotta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarlottaApplication

fun main(args: Array<String>) {
	runApplication<CarlottaApplication>(*args)
}
