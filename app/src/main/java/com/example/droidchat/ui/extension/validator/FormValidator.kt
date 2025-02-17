package com.example.droidchat.ui.extension.validator

internal interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}