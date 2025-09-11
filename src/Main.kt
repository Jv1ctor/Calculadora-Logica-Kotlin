import logicFunctions.LexSynAnalyser
import logicFunctions.Tokenizer
import TruthTable.TruthTableExecutor

/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */


fun main() {
    // val exp = "(A ∧ B) → C ∨ A"
    val exp = "~A ∨ B"

    val tokens = Tokenizer.generate(exp)
    println("Execução da validação sintatica e lexica: \n")
    if (LexSynAnalyser.run(tokens)) {
        println()
        val response = TruthTableExecutor.run(tokens)

        println("Execução da Tabela: \n")
        TruthTableExecutor.printTruthTable(tokens)
        print(response)
    } else {
        println("Fórmula mal formada (não é FBF)")
    }
}