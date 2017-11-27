package com.muoq.calculator2

import android.graphics.Path
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import com.muoq.calculator2.logic.*
import java.math.BigDecimal

class ButtonHandler(val expression: Expression) {

    private fun addNumberToExpression(num: Int) {
        val expLastIndex = expression.size - 1

        if (expression.isNumber(expLastIndex)) {
            val magnitudePlus = expression.getNumberAt(expLastIndex) * BigDecimal(10)
            expression.setNumberAt(expLastIndex, magnitudePlus + BigDecimal(num))
        } else {
            expression.setNumberAt(expLastIndex, BigDecimal(num))
        }
    }

    private fun addOperatorToExpression(operator: Operator) {
        val expLastIndex = expression.size - 1

        if (expression.isOperator(expLastIndex)) {
            if (expression.getOperatorAt(expLastIndex).hierarchy == Operator.MULTIPLY_HIERARCHY ||
                    expression.getOperatorAt(expLastIndex).hierarchy == Operator.POWER_HIERARCHY) {
                if (operator.hierarchy < Operator.PARENTHESIS_HIERARCHY) {
                    expression.setOperatorAt(expLastIndex, operator)
                }
            } else if (expression.getOperatorAt(expLastIndex).hierarchy == Operator.ADD_HIEREARCHY) {
                if (expression.getOperatorAt(expLastIndex).id == operator.id) {
                    expression.setOperatorAt(expLastIndex, Operator(Operator.ADD_ID))
                } else {
                    expression.setOperatorAt(expLastIndex, Operator(Operator.SUBTRACT_ID))
                }
            }
        }
    }

    fun setNumButtonListeners(numButtons: List<Button>) {
        for (button in numButtons) {
            button.setOnClickListener {view: View ->
                when(view.id) {
                    R.id.btn_0 -> {
                        addNumberToExpression(0)
                    }
                    R.id.btn_1 -> {
                        addNumberToExpression(1)
                    }
                    R.id.btn_2 -> {
                        addNumberToExpression(2)
                    }
                    R.id.btn_3 -> {
                        addNumberToExpression(3)
                    }
                    R.id.btn_4 -> {
                        addNumberToExpression(4)
                    }
                    R.id.btn_5 -> {
                        addNumberToExpression(5)
                    }
                    R.id.btn_6 -> {
                        addNumberToExpression(6)
                    }
                    R.id.btn_7 -> {
                        addNumberToExpression(7)
                    }
                    R.id.btn_8 -> {
                        addNumberToExpression(8)
                    }
                    R.id.btn_9 -> {
                        addNumberToExpression(9)
                    }
                }
            }
        }
    }

    fun setOperatorButtonListeners(operatorButtons: List<Button>) {
        for (button in operatorButtons) {
            button.setOnClickListener {view: View ->
                when(view.id) {
                    R.id.btn_multiply -> {
                        addOperatorToExpression(Operator(Operator.MULTIPLY_ID))
                    }
                    R.id.btn_divide -> {
                        addOperatorToExpression(Operator(Operator.DIVIDE_ID))
                    }
                    R.id.btn_add -> {
                        addOperatorToExpression(Operator(Operator.ADD_ID))
                    }
                    R.id.btn_subtract -> {
                        addOperatorToExpression(Operator(Operator.SUBTRACT_ID))
                    }
                }
            }
        }

        Log.i("")
    }

}
