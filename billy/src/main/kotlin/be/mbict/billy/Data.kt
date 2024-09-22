package be.mbict.billy

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/data")
class Controller(
    private val repo: DataRepository
) {

    @GetMapping
    fun getAllData() = repo

    @PostMapping
    fun process(@RequestBody data: Data) {
        repo.add(data)
    }
}

data class Data(
    val id: Int,
    val message: String
)

@Service
class DataRepository : ArrayList<Data>()