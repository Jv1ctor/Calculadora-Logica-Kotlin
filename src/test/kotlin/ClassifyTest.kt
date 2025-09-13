import TruthTable.TruthTableExecutor
import dto.ExpressionCorrect
import helpers.ReadJson
import logicFunctions.Tokenizer
import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ClassifyTest {

    companion object {
        @JvmStatic
        fun dataJson() = ReadJson.getData<ExpressionCorrect>("src/test/kotlin/data/expressions_correct.json")
    }

    @ParameterizedTest
    @MethodSource("dataJson")
    fun classifyValidationTest(data: ExpressionCorrect){
        val tokens = Tokenizer.generate(data.expression)

        val response = TruthTableExecutor.run(tokens)

        println("- Expression ${data.expression} is ${response.classify} \n")
        assertEquals(data.classify, response.classify)
    }

}