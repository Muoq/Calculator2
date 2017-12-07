package com.muoq.calculator2

import android.util.Log
import android.view.View
import android.widget.Button
import com.muoq.calculator2.logic.*
import com.muoq.calculator2.ui.TextOutput

class ButtonHandler(var expression: Expression, val textOutput: TextOutput) {

    companion object {
        val TAG = "CalculatorTAG"
        val TAGTHIS = "ButtonHandlerTAG"
    }

    private val stringChecker = StringChecker()

    var expressionString = ""

    constructor(expressionString: String, textOutput: TextOutput): this(Expression(expressionString), textOutput) {
        this.expressionString = expressionString
    }

    fun updateExpression() {
        expressionString = stringChecker.checkString(expressionString)
        expression = Expression(expressionString)
        textOutput.updateText(expressionString)
        Log.i(TAG, expression.toString())
    }

    fun setNumButtonListeners(numButtons: List<Button>) {
        for (button in numButtons) {
            button.setOnClickListener {view: View ->
                when(view.id) {
                    R.id.btn_0 -> {
                        expressionString+='0'
                    }
                    R.id.btn_1 -> {
                        expressionString+='1'
                    }
                    R.id.btn_2 -> {
                        expressionString+='2'
                    }
                    R.id.btn_3 -> {
                        expressionString+='3'
                    }
                    R.id.btn_4 -> {
                        expressionString+='4'
                    }
                    R.id.btn_5 -> {
                        expressionString+='5'
                    }
                    R.id.btn_6 -> {
                        expressionString+='6'
                    }
                    R.id.btn_7 -> {
                        expressionString+='7'
                    }
                    R.id.btn_8 -> {
                        expressionString+='8'
                    }
                    R.id.btn_9 -> {
                        expressionString+='9'
                    }
                }

                updateExpression()
            }
        }
    }

    fun setOperatorButtonListeners(operatorButtons: List<Button>) {
        for (button in operatorButtons) {
            button.setOnClickListener {view: View ->
                when(view.id) {
                    R.id.btn_open_parenthesis -> {
                        expressionString+='('
                    }
                    R.id.btn_close_parenthesis -> {
                        closeParenthesisChecker()
                    }
                    R.id.btn_multiply -> {
                        expressionString+='\u22C5'
                    }
                    R.id.btn_divide -> {
                        expressionString+='\u00F7'
                    }
                    R.id.btn_add -> {
                        expressionString+='+'
                    }
                    R.id.btn_subtract -> {
                        expressionString+='-'
                    }
                    R.id.btn_decimal -> {
                        expressionString+='.'
                    }
                }

                updateExpression()
            }
        }
    }

    fun closeParenthesisChecker() {
        var parenthesisSum = 0

        for (i in 0 until expressionString.length) {
            if (expressionString[i] == '(') {
                parenthesisSum++
            } else if (expressionString[i] == ')') {
                parenthesisSum--
            }
        }

        if (parenthesisSum == 0)
            expressionString = "($expressionString)"
        else
            expressionString += ')'
    }

    fun closeParenthesisDelHandler(string: String): String {
        var result = string
        var parenthesisSum = 1

        for (i in result.length - 1 downTo 0) {
            if (result[i] == ')')
                parenthesisSum++
            else if (result[i] == '(')
                parenthesisSum--

            if (parenthesisSum == 0) {
                if (i == 0) {
                    result = result.filterIndexed {index, _ -> index > 0}
                } else {
                    return result
                }
            }
        }

        return result
    }

    fun setFunctionalButtonListeners(functionalButtons: List<Button>) {
        for (button in functionalButtons) {
            button.setOnClickListener {view: View ->
                when(view.id) {
                    R.id.btn_ac -> {
                        expressionString = ""
                    }
                    R.id.btn_del -> {
                        var tempString = ""
                        for (i in 0 until expressionString.length - 1) {
                            tempString+=expressionString[i]
                        }

                        when (expressionString.last()) {
                            ')' -> {
                                tempString = closeParenthesisDelHandler(tempString)
                            }
                        }

                        expressionString = tempString
                    }
                    R.id.btn_equals -> {
                        expressionString = Solver().solve(expression).toString()
                    }
                }
                updateExpression()
            }
        }
    }

}
