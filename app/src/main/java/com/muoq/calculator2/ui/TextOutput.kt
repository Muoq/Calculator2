package com.muoq.calculator2.ui

import android.widget.TextView

class TextOutput(val textView: TextView) {

    fun updateText(expressionString: String) {
        textView.text = expressionString
    }

}
