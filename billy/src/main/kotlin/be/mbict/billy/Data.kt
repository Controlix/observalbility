package be.mbict.billy

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger { }

@RestController
@RequestMapping("/data")
class Controller(
    private val repo: DataRepository
) {

    @GetMapping
    fun getAllData(): DataRepository {
        log.info { "Retrieve all data" }
        return repo
    }

    @PostMapping
    fun process(@RequestBody data: Data) {
        log.info { "Add $data" }
        repo.add(data)
    }
}

data class Data(
    val id: Int,
    val message: String
)

@Service
class DataRepository : ArrayList<Data>()