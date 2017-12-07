package com.muoq.calculator2.logic

class StringChecker {

    companion object {
        val validOperators = listOf('\u005E', '\u221A', 'p', 'r', '(', ')', '\u22C5', '\u00F7', '*', '/', '+', '-')
        val numberChars = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
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

        result = result.replace("0E-${Operator.SCALE}", "0")

        if (expressionString.isNotEmpty()) {
            result = checkParentheses(result)
            result = removeConsecutiveTypes(validOperators.filter {char -> char != '(' && char != ')'}, result)
            result = removeConsecutiveTypes(listOf('.'), result)
        } else {
            result = "0"
        }

        result = regulateZero(result)
        if (result.isEmpty())
            result = "0"

        return result
    }

    fun checkParentheses(string: String): String {
        var result = string

        var loopCtr = 0
        while (loopCtr < result.length) {

            if (result[loopCtr] == '(') {
                try {
                    if (result[loopCtr + 1] in validOperators.filter {it != '(' && it != ')'}) {
                        val tempChar = result[loopCtr + 1]
                        result = result.filterIndexed {index, _ -> index < loopCtr}
                        result += tempChar
                        continue
                    }
                } catch (ex: StringIndexOutOfBoundsException) {}
            } else if (result[loopCtr] == ')') {
                if (result[loopCtr - 1] in validOperators.filter {it != '(' && it != ')'}) {
                    result = result.filterIndexed {index, _ -> index < loopCtr - 1}
                    result += ')'
                    continue
                }
            }

            loopCtr++
        }

        return result
    }


    fun regulateZero(string: String): String {
        var startingIndex = 0

        //Removes preceding zeros
        for (i in 0 until string.length) {
            if (string[i] == '0') {
                startingIndex = i + 1
            } else if (string[i] in validOperators.filter {it != '(' && it != ')'}) {
                startingIndex = 0
                break
            } else {
                break
            }
        }

        val result = string.filterIndexed {index, _ -> index >= startingIndex}

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
