package com.muoq.calculator2.logic

import java.math.BigDecimal

class Solver {

    private var remainingClosingParentheses = 0

    fun solve(expressionArg: Expression): BigDecimal {
        val expression = Expression(expressionArg)
        var deletedParentheses = 0

        var loopCtr = 0
        while (loopCtr < expression.size) {
            if (expression.isOperator(loopCtr)) {
                if (expression.getOperatorAt(loopCtr).id == Operator.CLOSE_PARENTHESIS_ID) {
                    for (i in 0 until expression.size - loopCtr) {
                        expression.removeAt(loopCtr)
                    }

                    return solveSimple(expression)
                }

                if (expression.getOperatorAt(loopCtr).id == Operator.OPEN_PARENTHESIS_ID) {
                    val tempExpression = Expression(loopCtr + 1, expression)
                    val parenthesisSolution = solve(tempExpression)
                    expression.setNumberAt(loopCtr, parenthesisSolution)
                    remainingClosingParentheses++

                    for (i in loopCtr + 1 until expression.size) {
                        if (deletedParentheses == remainingClosingParentheses) {
                            break
                        }

                        if (expression.isOperator(loopCtr + 1)) {
                            if (expression.getOperatorAt(loopCtr + 1).id == Operator.CLOSE_PARENTHESIS_ID) {
                                deletedParentheses++
                            }
                        }

                        expression.removeAt(loopCtr + 1)
                    }
                }

            }

            loopCtr++
        }

        remainingClosingParentheses = 0
        return solveSimple(expression)
    }

    private fun solveSimple(expressionArg: Expression): BigDecimal {
        val expression = Expression(expressionArg)

        for (i in Operator.HIERARCHY_AMOUNT - 1 downTo 0) {
            var loopCtr = 0
            while (loopCtr < expression.size) {

                if (expression.isOperator(loopCtr) && expression.getOperatorAt(loopCtr).hierarchy == i) {
                    val operationTerms = expression.getSurroundingNumbers(loopCtr)
                    val operationSolution = expression.getOperatorAt(loopCtr).operation(operationTerms.first, operationTerms.second)
                    expression.setNumberAt(loopCtr - 1, operationSolution)
                    expression.removeAt(loopCtr)
                    expression.removeAt(loopCtr)
                    loopCtr--
                }

                loopCtr++
            }
        }

        return expression.getNumberAt(0)
    }

}
