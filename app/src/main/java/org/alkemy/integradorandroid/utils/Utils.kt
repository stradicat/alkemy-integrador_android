package org.alkemy.integradorandroid.utils

import java.util.regex.Pattern

class Utils {

    // Regular expression to validate input, so that it's only digits,
    // with an extension between 1 and 3 [1...999]
    private val regExp: Pattern =
        Pattern.compile("\\d{1,3}")

    fun validateInput(input: String): Boolean {
        return regExp.toRegex().matches(input)
    }


}