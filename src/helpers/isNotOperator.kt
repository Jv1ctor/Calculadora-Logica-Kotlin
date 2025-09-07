package helpers

import data.TokensExpr

fun isNotOperator(exp: TokensExpr?) =  when(exp){
    TokensExpr.OP_NOT -> true
    else -> false
}