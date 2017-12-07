package com.muoq.calculator2.logic

import java.math.BigDecimal
import java.security.InvalidParameterException

open class Operator() {

    companion object {
        val SCALE = 18

        val validOperators = listOf('(', ')', 'p','r', '*', '/', '+', '-')

        val powerOperation = { x: BigDecimal, y: BigDecimal -> BigDecimal(Math.pow(x.toDouble(), y.toDouble()))}
        val rootOperation = {x: BigDecimal, y: BigDecimal ->
            val xDouble = x.toDouble()
            val yDouble = y.toDouble()

            BigDecimal(Math.pow(yDouble, 1 / xDouble))
        }
        val multiplyOperation = {x: BigDecimal, y: BigDecimal ->
            if (x.compareTo(BigDecimal(0)) == 1) {
                BigDecimal(0).setScale(SCALE, BigDecimal.ROUND_HALF_UP)
            } else if (y.compareTo(BigDecimal(0)) == 1) {
                BigDecimal(0).setScale(SCALE, BigDecimal.ROUND_HALF_UP)
            }
            x.setScale(SCALE, BigDecimal.ROUND_HALF_UP) * y
        }
        val divideOperation = {x: BigDecimal, y: BigDecimal -> x.setScale(SCALE, BigDecimal.ROUND_HALF_UP) / y}
        val addOperation = {x: BigDecimal, y: BigDecimal -> x.setScale(SCALE, BigDecimal.ROUND_HALF_UP) + y}
        val subtractOperation = {x: BigDecimal, y: BigDecimal -> x.setScale(SCALE, BigDecimal.ROUND_HALF_UP) - y}

        val OPEN_PARENTHESIS_ID = 7
        val CLOSE_PARENTHESIS_ID = 6
        val POWER_ID = 5
        val ROOT_ID = 4
        val MULTIPLY_ID = 3
        val DIVIDE_ID = 2
        val ADD_ID = 1
        val SUBTRACT_ID = 0

        val PARENTHESIS_HIERARCHY = 4
        val POWER_HIERARCHY = 2
        val MULTIPLY_HIERARCHY = 1
        val ADD_HIERARCHY = 0

        val emptyOperation: (BigDecimal, BigDecimal) -> BigDecimal = {x, y -> x}

        val operatorsList = listOf(emptyOperation, emptyOperation, powerOperation, rootOperation, multiplyOperation,
                                    divideOperation, addOperation, subtractOperation)
        val idList = listOf(OPEN_PARENTHESIS_ID, CLOSE_PARENTHESIS_ID,POWER_ID, ROOT_ID,
                            MULTIPLY_ID, DIVIDE_ID, ADD_ID, SUBTRACT_ID)

        val HIERARCHY_AMOUNT = 4
    }

    var operation = emptyOperation
    private var operatorTypeChar = ' '

    var id = -1
    var hierarchy = -1

    constructor(operatorChar: Char): this() {
        if (operatorChar !in validOperators) {
            throw InvalidParameterException("Error: \'" + operatorChar.toString() + "\' is not a valid operator.")
        }

        operatorTypeChar = operatorChar
        operation = operatorsList[validOperators.indexOf(operatorChar)]
        id = idList[validOperators.indexOf(operatorChar)]
        hierarchy = id / 2
    }

    constructor(operatorID: Int): this() {
        if (operatorID !in SUBTRACT_ID..CLOSE_PARENTHESIS_ID) {
            throw throw InvalidParameterException("Error: \'" + operatorID.toString() + "\' is not a valid operator ID.")
        }

        when(operatorID) {
            OPEN_PARENTHESIS_ID -> {
                operatorTypeChar = '('
                operation = emptyOperation
                id = operatorID
                hierarchy = id / 2
            }
            CLOSE_PARENTHESIS_ID -> {
                operatorTypeChar = ')'
                operation = emptyOperation
                id = operatorID
                hierarchy = id / 2
            }
            POWER_ID -> {
                operatorTypeChar = 'p'
                operation = powerOperation
                id = operatorID
                hierarchy = id / 2
            }
            ROOT_ID -> {
                operatorTypeChar = 'r'
                operation = rootOperation
                id = operatorID
                hierarchy = id / 2
            }
            MULTIPLY_ID -> {
                operatorTypeChar = '*'
                operation = multiplyOperation
                id = operatorID
                hierarchy = id / 2
            }
            DIVIDE_ID -> {
                operatorTypeChar = '/'
                operation = divideOperation
                id = operatorID
                hierarchy = id / 2
            }
            ADD_ID -> {
                operatorTypeChar = '+'
                operation = addOperation
                id = operatorID
                hierarchy = id / 2
            }
            SUBTRACT_ID -> {
                operatorTypeChar = '-'
                operation = subtractOperation
                id = operatorID
                hierarchy = id / 2
            }
        }
    }

    override fun toString(): String {
        return operatorTypeChar.toString()
    }

}
