package com.example.platform.extension

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

public fun KeyboardType.getVisualTransformationForPassword(visibility: Boolean): VisualTransformation {
    return if (this == KeyboardType.Password) {
        if (visibility) VisualTransformation.None
        else PasswordVisualTransformation()
    } else VisualTransformation.None
}