package dto
import kotlinx.serialization.Serializable

@Serializable
data class ExpressionWrong(val expression: String, val error: String)
