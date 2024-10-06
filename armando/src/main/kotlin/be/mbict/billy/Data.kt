package be.mbict.billy

import io.github.oshai.kotlinlogging.KotlinLogging
import org.slf4j.LoggerFactory
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
    private val billy: RestTemplate
) {

    @GetMapping
    fun getAllData(): ResponseEntity<Array<Data>> {
        log.info { "Getting all data..." }
        return billy.getForEntity("/data", Array<Data>::class.java)
    }

    @PostMapping
    fun addData(@RequestBody data: Data): ResponseEntity<String> {
        log.info { "Adding data $data ..." }
        return billy.postForEntity("/data", data, String::class.java)
    }
}

data class Data(
    val id: Int,
    val message: String
)

@Configuration
internal class DataConfig {

    @Bean
    internal fun restTemplate(restTemplateBuilder: RestTemplateBuilder) = restTemplateBuilder.rootUri("http://localhost:8081").build()
}