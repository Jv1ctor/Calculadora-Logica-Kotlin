package logicFunctions

import data.ResponseToken
import data.TokensExpr
import helpers.isNotOperator
import helpers.isOperator

object LexSynAnalyser {

    fun run(tokenizer: ResponseToken): Boolean {
        var beforeExp: TokensExpr? = null

        if(tokenizer.countOpenParenthese != tokenizer.countCloseParenthese){
            println("Erro de sintaxe parenteses inconsistentes")
            return false
        }

        for (token in tokenizer.listExpr) {
            val isValid = when {
                token == TokensExpr.OPEN_PARENTHESE &&
                        (beforeExp == null || isOperator(beforeExp) || beforeExp == TokensExpr.OPEN_PARENTHESE) -> true

                token == TokensExpr.TERM &&
                        (beforeExp == TokensExpr.OPEN_PARENTHESE || isOperator(beforeExp)) -> true

                isOperator(token) &&
                        (beforeExp == TokensExpr.TERM || beforeExp == TokensExpr.CLOSE_PARENTHESE) -> true

                isNotOperator(token) &&
                        (beforeExp == TokensExpr.OPEN_PARENTHESE || beforeExp == null)  -> true


                token == TokensExpr.CLOSE_PARENTHESE &&
                        (beforeExp == TokensExpr.TERM || beforeExp == TokensExpr.CLOSE_PARENTHESE) -> true

                else -> false
            }

            if (!isValid) {
                println("Erro de sintaxe em: $token (anterior: $beforeExp)")
                return false
            }

            println("OK: $token depois de $beforeExp")
            beforeExp = token
        }

        return true
    }
}