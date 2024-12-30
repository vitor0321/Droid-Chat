package com.example.droidchat.ui.validator

internal interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}