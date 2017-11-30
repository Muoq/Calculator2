package com.muoq.calculator2.logic

class StringChecker {

    companion object {
        val validOperators = listOf('\u005E', '\u221A', 'p', 'r', '(', ')', '\u22C5', '\u00F7', '*', '/', '+', '-')
        val numbersChars = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    }

    fun checkString(expressionString: String): String {
        var result = ""

        expressionString.forEach {char ->
            if (char == '\u005E') {
                result += 'p'
            } else if (char == '\u221A') {
                result += 'r'
            } else if (char == '\u22C5') {
                result += '*'
            } else if (char == '\u00F7') {
                result += '/'
            } else {
                result += char
            }
        }

        if (expressionString.isNotEmpty()) {
            result = removeConsecutiveTypes(validOperators.toList(), result)
            result = removeConsecutiveTypes(listOf('.'), result)
        }

        return result
    }

    fun removeConsecutiveTypes(typeList: List<Char>, string: String): String {
        var result = string

        var deleteAfterIndex = result.lastIndex
        if (string.last() in typeList) {
            for (i in result.lastIndex downTo 0) {
                if (result[i] in typeList) {
                    deleteAfterIndex = i
                } else {
                    break
                }
            }
        }

        val tempResult = result
        result = ""
        for (i in 0 until deleteAfterIndex) {
            result+= tempResult[i]
        }
        result += tempResult.last()

        return result
    }

}
