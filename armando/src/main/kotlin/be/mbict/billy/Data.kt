package be.mbict.armando

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/data")
class Controller(
    private val restTemplate: RestTemplate
) {

    @GetMapping
    fun getAllData(): ResponseEntity<Array<Data>> {
        log.info { "Getting all data..." }
        return restTemplate.getForEntity("http://localhost:8081/data", Array<Data>::class.java)
    }

    @PostMapping
    fun addData(@RequestBody data: Data): ResponseEntity<String> {
        log.info { "Adding data $data ..." }
        return restTemplate.postForEntity("http://localhost:8082/data", data, String::class.java)
    }
}

data class Data(
    val id: Int,
    val message: String
)

@Configuration
internal class DataConfig {

    @Bean
    internal fun restTemplate(restTemplateBuilder: RestTemplateBuilder) = restTemplateBuilder.build()
}