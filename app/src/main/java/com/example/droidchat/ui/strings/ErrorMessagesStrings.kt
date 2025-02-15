package com.example.droidchat.ui.strings

internal data class ErrorMessagesStrings(
    val errorMessageUnexpected: String = "Ocorreu um erro inesperado ao abrir o aplicativo\n\nPor favor, tente novamente mais tarde",
    val errorBottomUnexpected: String = "Fechar aplicativo",
    val errorMessageInvalidUsernameOrPassword: String = "E-mail ou senha incorretos",
    val errorMessageUserWithUsernameAlreadyExists: String = "Usuário com o e-mail fornecido já existe no sistema",
    val errorMessageFieldBlank: (String) -> String = { "$it é obrigatório" },
    val errorMessagePasswordLessThanEightChars: String = "Senha deve conter no mínimo 8 caracteres",
    val errorMessagePasswordInvalid: String = "Senha deve conter pelo menos uma letra e número",
    val errorMessagePasswordConfirmationInvalid: String = "Confirmação de senha não corresponde",
    val errorMessageEmailInvalid: String = "E-mail inválido",
    val errorMessageApiFormValidationFailed: String = "Erro de validação de formulário, confira os dados e tente novamente",
    val errorMessageApiFormUploadImageFailed: String = "Erro ao fazer upload da foto de perfil, tente novamente (formatos aceitos: .jpg, .jpeg, .png)",
    val commonGenericErrorTitle: String = "Alguma coisa deu errado",
    val commonGenericErrorMessage: String = "Tente novamente ou reporte um problema",
    val commonServiceUnavailable: String = "Serviço fora do ar, tente novamente mais tarde",
    val commonTryAgain: String = "Tentar novamente",
)