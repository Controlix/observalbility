package be.mbict.carlotta

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.jms.core.JmsTemplate
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger { }

@RestController
@RequestMapping("/data")
class Controller(
    private val jmsTemplate: JmsTemplate
) {

    @PostMapping
    fun publish(@RequestBody data: Data) {
        log.info { "Publish $data" }
        jmsTemplate.convertAndSend("data-stream", data)
    }
}

class Data(
    val id: Int,
    val message: String
)