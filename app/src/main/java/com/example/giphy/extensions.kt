package com.example.giphy

import android.widget.EditText

fun EditText.isValid(minLength: Int): Boolean {
    error = if (length() < minLength) {
        context.getText(R.string.not_valid_search_request_error)
    } else {
        null
    }
    return length() >= minLength
}