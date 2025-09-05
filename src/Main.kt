/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */

enum class OperatorEnum(val order: Int){
    OP_NOT(1),
    OP_AND(2),
    OP_OR(3),
    OP_EXOR(3),
    OP_THEN(4),
    OP_EXTHEN(4)
}

enum class ExpOrder(val ordem: Int?) {
    OPEN_PARENTHESE(0),
    CLOSE_PARENTHESE(0),
    OP_NOT(1),
    OP_AND(2),
    OP_OR(3),
    OP_EXOR(3),
    OP_THEN(4),
    OP_EXTHEN(4),
    TERM(5),
    UNDERFINED(null)
}

data class TokenExp(val list: MutableList<ExpOrder>, val countOpenParenthese: Int, val countCloseParenthese: Int)

fun tokenizerExp(exp: String): TokenExp {
    // separa a expressao com uma lista de cada caractere
    val expInArr = exp.replace(" ", "").trim().split("").drop(1).dropLast(1)
    // lista tokenizada
    val listOfExpressionOrder: MutableList<ExpOrder>  = mutableListOf()
    var countOpen: Int = 0
    var countClose: Int = 0
    for(exp in expInArr){
        when(exp){
            "∧" -> {
                listOfExpressionOrder.add(ExpOrder.OP_AND)
            }
            "→"  -> {
                listOfExpressionOrder.add(ExpOrder.OP_THEN)
            }
            "+" -> listOfExpressionOrder.add(ExpOrder.OP_OR)
            "(" -> {
                countOpen += 1
                listOfExpressionOrder.add(ExpOrder.OPEN_PARENTHESE)
            }
            ")" -> {
                countClose += 1
                listOfExpressionOrder.add(ExpOrder.CLOSE_PARENTHESE)
            }
            else -> {
                if (exp.matches(Regex("^[a-zA-Z]$"))) {
                    listOfExpressionOrder.add(ExpOrder.TERM)
                } else {
                    listOfExpressionOrder.add(ExpOrder.UNDERFINED)
                }
            }
        }
    }

    return TokenExp(listOfExpressionOrder, countOpen, countClose)
}

fun isOperator(exp: ExpOrder?) = (exp == ExpOrder.OP_AND || exp == ExpOrder.OP_THEN || exp == ExpOrder.OP_OR)

fun sintaxeAnalise(exp: String): Boolean {

    val tokenizer: TokenExp = tokenizerExp(exp)
    println(tokenizer)



    var beforeExp: ExpOrder? = null

    if(tokenizer.countOpenParenthese != tokenizer.countCloseParenthese){
        println("Erro de sintaxe parenteses inconsistentes")
        return false
    }

    for (token in tokenizer.list) {
        val isValid = when {
            token == ExpOrder.OPEN_PARENTHESE &&
                    (beforeExp == null || isOperator(beforeExp) || beforeExp == ExpOrder.OPEN_PARENTHESE) -> true

            token == ExpOrder.TERM &&
                    (beforeExp == ExpOrder.OPEN_PARENTHESE || isOperator(beforeExp)) -> true

            isOperator(token) &&
                    (beforeExp == ExpOrder.TERM || beforeExp == ExpOrder.CLOSE_PARENTHESE) -> true

            token == ExpOrder.CLOSE_PARENTHESE &&
                    (beforeExp == ExpOrder.TERM || beforeExp == ExpOrder.CLOSE_PARENTHESE) -> true

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

fun main() {
    val exp = "(A ∧ B) → C + D"

    print(sintaxeAnalise(exp))

}