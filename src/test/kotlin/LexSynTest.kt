
import TruthTable.TruthTableExecutor
import dto.ExpressionCorrect
import dto.ExpressionWrong
import helpers.ReadJson
import logicFunctions.LexSynAnalyser
import logicFunctions.Tokenizer
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource


class LexSynTest {
    companion object {
        @JvmStatic
        fun dataJsonCorrect() = ReadJson.getData<ExpressionCorrect>("src/test/kotlin/data/expressions_correct.json")

        @JvmStatic
        fun dataJsonWrong() = ReadJson.getData<ExpressionWrong>("src/test/kotlin/data/expressions_wrong.json")
    }

    @ParameterizedTest
    @MethodSource("dataJsonCorrect")
    fun lexSynCorrectTest(data: ExpressionCorrect){
        val tokens = Tokenizer.generate(data.expression)
        val validation = LexSynAnalyser.run(tokens)
        println("- Expression ${data.expression} is ${if(validation) "valid" else "not valid"}")
        println("- $tokens \n")
        assertEquals(true, validation, "Analise lexica e sintatica validas")
    }


    @ParameterizedTest
    @MethodSource("dataJsonWrong")
    fun lexSynWrongTest(data: ExpressionWrong){
        val tokens = Tokenizer.generate(data.expression)
        val validation = LexSynAnalyser.run(tokens)
        println("- Expression ${data.expression} is ${if(validation) "valid" else "not valid because ${data.error}"}")
        println("- $tokens \n")
        assertEquals(false, validation, "Analise lexica e sintatica invalidas")
    }
}
