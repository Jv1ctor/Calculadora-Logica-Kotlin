package logicFunctions

import data.ResponseToken
import data.Term
import data.TokensExpr

object Tokenizer {
    private val listOfExpressionOrder: MutableList<TokensExpr> = mutableListOf()
    private val listOfTerm: MutableList<Term> = mutableListOf()
    private var countOpen: Int = 0
    private var countClose: Int = 0

    fun generate(exp: String): ResponseToken {
        // separa a expressao com uma lista de cada caractere
        val expInArr = exp.replace(" ", "").trim().split("").drop(1).dropLast(1)

        // lista tokenizada
        for(exp in expInArr){
            when(exp){
                "∧" -> listOfExpressionOrder.add(TokensExpr.OP_AND)
                "→" -> listOfExpressionOrder.add(TokensExpr.OP_THEN)
                "∨" -> listOfExpressionOrder.add(TokensExpr.OP_OR)
                "~" -> listOfExpressionOrder.add(TokensExpr.OP_NOT)
                "↔" -> listOfExpressionOrder.add(TokensExpr.OP_EXTHEN)
                "⊻" -> listOfExpressionOrder.add(TokensExpr.OP_EXOR)
                "(" -> {
                    countOpen += 1
                    listOfExpressionOrder.add(TokensExpr.OPEN_PARENTHESE)
                }
                ")" -> {
                    countClose += 1
                    listOfExpressionOrder.add(TokensExpr.CLOSE_PARENTHESE)
                }
                else -> {
                    if (exp.matches(Regex("^[A-Z]$"))) {
                        listOfExpressionOrder.add(TokensExpr.TERM)
                        listOfTerm.add(Term(exp))
                    } else {
                        listOfExpressionOrder.add(TokensExpr.UNDEFINED)
                    }
                }
            }
        }

        return ResponseToken(listOfExpressionOrder, listOfTerm, countOpen, countClose)
    }
}