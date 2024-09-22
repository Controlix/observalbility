package be.mbict.billy

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.ParameterizedTypeReference
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.lang.reflect.ParameterizedType

@RestController
@RequestMapping("/data")
class Controller(
    private val billy: RestTemplate
) {

    @GetMapping
    fun getAllData() = billy.getForEntity("/data", Array<Data>::class.java)

    @PostMapping
    fun process(@RequestBody data: Data) = billy.postForEntity("/data", data, String::class.java)
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