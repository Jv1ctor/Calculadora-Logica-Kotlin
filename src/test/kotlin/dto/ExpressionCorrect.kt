package dto

import data.ClassificationTable
import kotlinx.serialization.Serializable

@Serializable
data class ExpressionCorrect(val expression: String, val classify: ClassificationTable)
