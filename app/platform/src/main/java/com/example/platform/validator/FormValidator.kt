package com.example.platform.validator

public interface FormValidator<FormState> {
    fun validate(formState: FormState): FormState
}