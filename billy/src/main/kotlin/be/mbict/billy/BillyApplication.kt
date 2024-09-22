package be.mbict.billy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BillyApplication

fun main(args: Array<String>) {
	runApplication<BillyApplication>(*args)
}
