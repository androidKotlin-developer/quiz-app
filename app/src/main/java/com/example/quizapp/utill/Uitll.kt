package com.example.quizapp.utill

object Uitll {
    fun nullChecker(string: String?): String? {
        var returnString: String? = ""
        if (string != null && string.isNotEmpty() && !string.contains("null")) {
            returnString = string
        }
        return returnString
    }
}
