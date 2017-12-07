package com.muoq.calculator2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import com.muoq.calculator2.logic.Expression
import com.muoq.calculator2.ui.TextOutput

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val exp1 = Expression("3*4-(4+5-3-81/9/3*7*(31-16+(7-5) + (3 + 6)) + 5*4 - 2) - 3")
        val exp1 = Expression()

        val buttonHandler = ButtonHandler(exp1, TextOutput(findViewById(R.id.expression_view)))

        val numButtons: MutableList<Button> = mutableListOf()
        val operatorButtons: MutableList<Button> = mutableListOf()
        val functionalButtons: MutableList<Button> = mutableListOf()

        numButtons.add(findViewById(R.id.btn_0))
        numButtons.add(findViewById(R.id.btn_1))
        numButtons.add(findViewById(R.id.btn_2))
        numButtons.add(findViewById(R.id.btn_3))
        numButtons.add(findViewById(R.id.btn_4))
        numButtons.add(findViewById(R.id.btn_5))
        numButtons.add(findViewById(R.id.btn_6))
        numButtons.add(findViewById(R.id.btn_7))
        numButtons.add(findViewById(R.id.btn_8))
        numButtons.add(findViewById(R.id.btn_9))

        operatorButtons.add(findViewById(R.id.btn_open_parenthesis))
        operatorButtons.add(findViewById(R.id.btn_close_parenthesis))
        operatorButtons.add(findViewById(R.id.btn_multiply))
        operatorButtons.add(findViewById(R.id.btn_divide))
        operatorButtons.add(findViewById(R.id.btn_add))
        operatorButtons.add(findViewById(R.id.btn_subtract))
        operatorButtons.add(findViewById(R.id.btn_decimal))

        functionalButtons.add(findViewById(R.id.btn_ac))
        functionalButtons.add(findViewById(R.id.btn_del))
        functionalButtons.add(findViewById(R.id.btn_equals))

        buttonHandler.setNumButtonListeners(numButtons)
        buttonHandler.setOperatorButtonListeners(operatorButtons)
        buttonHandler.setFunctionalButtonListeners(functionalButtons)
        buttonHandler.updateExpression()

        val textOutput = TextOutput(findViewById(R.id.expression_view))
    }
}
