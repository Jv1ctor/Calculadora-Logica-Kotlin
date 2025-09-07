package logicFunctions

import data.ResponseToken

object TruthTableExecutor {
    fun run(response: ResponseToken) {
        val rpn = RPNConverter.toRPN(response.listExpr, response.listTerm)
        val assignments = TruthTableGenerator.allCombinations(response.listTerm)
        val results = assignments.map { RPNEvaluator.evaluate(rpn, it, response.listTerm) }

        // Imprime a tabela
        printTruthTable(assignments, results)

        // Classificação
        when {
            results.all { it } -> println("Classificação: Tautologia")
            results.all { !it } -> println("Classificação: Contradição")
            else -> println("Classificação: Contingência")
        }
    }

    private fun printTruthTable(assignments: List<Map<String, Boolean>>, results: List<Boolean>) {
        if (assignments.isEmpty()) return

        // Cabeçalho
        val headers = assignments[0].keys.toList()
        println(headers.joinToString(" | ") + " | Result")
        println("-".repeat(headers.size * 6 + 8))

        // Linhas
        for (i in assignments.indices) {
            val values = headers.map { if (assignments[i][it] == true) "1" else "0" }
            println(values.joinToString(" | ") + " | " + if (results[i]) "1" else "0")
        }
    }


}