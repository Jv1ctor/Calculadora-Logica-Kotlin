import logicFunctions.LexSynAnalyser
import logicFunctions.Tokenizer
import logicFunctions.TruthTableGenerator

/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */


fun main() {
    val exp = "(A ∧ B) → C ∨ A"

    val tokens = Tokenizer.generate(exp)

//    println(tokens.listExpr)
    val analyser = LexSynAnalyser.run(tokens)

    val result = TruthTableGenerator.allCombinations(tokens.listTerm)

//    print(result)
//    for (r in result){
//
//        println("${r["A"]} and ${r["B"]} = ${logicFunctions.Operators.and(r["A"], r["B"])}");
//    }
}