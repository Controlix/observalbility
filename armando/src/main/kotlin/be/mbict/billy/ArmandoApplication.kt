package be.mbict.armando

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ArmandoApplication

fun main(args: Array<String>) {
	runApplication<ArmandoApplication>(*args)
}
