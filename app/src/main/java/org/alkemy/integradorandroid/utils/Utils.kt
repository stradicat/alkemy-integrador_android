package org.alkemy.integradorandroid.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class Utils {

    // Regular expression to validate input, so that it's only digits,
    // with an extension between 1 and 3 [1...999]
    private val regExp: Pattern =
        Pattern.compile("\\d{1,3}")

    fun validateInput(input: String): Boolean {
        return regExp.toRegex().matches(input)
    }

    // Show a custom message
    fun snackBar(view: View, message: String){
        Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

}