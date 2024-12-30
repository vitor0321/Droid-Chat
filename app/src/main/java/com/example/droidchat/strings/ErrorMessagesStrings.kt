package com.walcker.droidchat.strings

internal data class ErrorMessagesStrings(
    val errorMessageInvalidUsernameOrPassword: String = "E-mail ou senha incorretos",
    val errorMessageUserWithUsernameAlreadyExists: String = "Usuário com o e-mail fornecido já existe no sistema",
    val errorMessageFieldBlank: String = "%1s é obrigatório",
    val errorMessagePasswordLessThanEightChars: String = "Senha deve conter no mínimo 8 caracteres",
    val errorMessagePasswordInvalid: String = "Senha deve conter pelo menos uma letra e número",
    val errorMessagePasswordConfirmationInvalid: String = "Confirmação de senha não corresponde",
    val errorMessageEmailInvalid: String = "E-mail inválido"
)