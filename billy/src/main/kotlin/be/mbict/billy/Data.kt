package be.mbict.billy

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
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
    fun allData(): Iterable<Data> {
        log.info { "Retrieve all data" }
        return repo.findAll()
    }

    @PostMapping
    fun add(@RequestBody data: Data) {
        log.info { "Add $data" }
        repo.save(data)
    }
}

@Entity
class Data(
    @Id val id: Int,
    val message: String
)

@Repository
interface DataRepository : JpaRepository<Data, Int>