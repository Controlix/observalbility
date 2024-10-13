package be.mbict.billy

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger { }

@RestController
@RequestMapping("/data")
class Controller(private val repo: DataRepository) {

    @GetMapping
    fun allData(): Iterable<Data> {
        log.info { "Retrieve all data" }
        return repo.findAll()
    }
}

@Entity
class Data(
    @Id val id: Int,
    val message: String
)

@Repository
interface DataRepository : JpaRepository<Data, Int>

@Component
class JmsListener(private val repo: DataRepository) {

    @Transactional
    @JmsListener(destination = "data-stream")
    fun addData(data: Data) {
        log.info { "Received $data" }
        repo.save(data)
    }
}