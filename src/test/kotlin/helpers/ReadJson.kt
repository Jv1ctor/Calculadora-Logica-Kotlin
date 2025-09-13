package helpers

import dto.ExpressionCorrect
import kotlinx.serialization.json.Json
import java.io.File

object ReadJson {
    inline fun<reified T> getData(path: String): List<T>{
        val jsonText = File(path).readText()
        val expressions = Json.decodeFromString<List<T>>(jsonText)

        return expressions
    }
}
