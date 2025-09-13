import TruthTable.TruthTableExecutor
import dto.ExpressionCorrect
import helpers.ReadJson
import logicFunctions.LexSynAnalyser
import logicFunctions.Tokenizer
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MainTest {

    companion object {
        @JvmStatic
        fun dataJson() = ReadJson.getData<ExpressionCorrect>("src/test/kotlin/data/expressions_correct.json")
    }

    @ParameterizedTest
    @MethodSource("dataJson")
    fun lexSynValidationTest(data: ExpressionCorrect){
        val tokens = Tokenizer.generate(data.expression)
        val validation = LexSynAnalyser.run(tokens)
        println("- Expression ${data.expression} is ${if(validation) "valid" else "not valid"}")
        println("- $tokens \n")
        assertEquals(true, validation, "Analise lexica e sintatica validas")
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