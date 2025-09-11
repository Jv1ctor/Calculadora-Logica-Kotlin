package data

data class ResponseToken(
    val listExpr: MutableList<TokensExpr>,
    val listTerm: MutableList<Term>,
    val countOpenParenthese: Int,
    val countCloseParenthese: Int)
