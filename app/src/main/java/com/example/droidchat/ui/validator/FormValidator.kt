package com.example.droidchat.ui.validator

public interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}