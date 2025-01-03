package com.example.droidchat.ui.validator

public object PasswordValidator {

    fun isValid(value: String): Boolean {
        return value.length >= 8 && value.any { it.isDigit() } && value.any { it.isLetter() }
    }
}