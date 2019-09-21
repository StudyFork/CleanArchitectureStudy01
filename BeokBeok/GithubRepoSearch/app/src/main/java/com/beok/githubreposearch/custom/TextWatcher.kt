package com.beok.githubreposearch.custom

import android.text.Editable
import android.text.TextWatcher

open class TextWatcher : TextWatcher {

    override fun afterTextChanged(editable: Editable?) {
        // NO OP
    }

    override fun beforeTextChanged(
        char: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {
        // NO OP
    }

    override fun onTextChanged(
        char: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {
        // NO OP
    }
}