package com.example.droidchat.ui.di

import com.example.droidchat.ui.feature.signin.viewModel.SignInFormValidator
import com.example.droidchat.ui.feature.signin.viewModel.SignInState
import com.example.droidchat.ui.feature.signup.viewModel.SignUpFormValidator
import com.example.droidchat.ui.feature.signup.viewModel.SignUpState
import com.example.droidchat.ui.validator.FormValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface FormValidatorModule {

    @Binds
    fun bindSignUpFormValidator(formValidator: SignUpFormValidator): FormValidator<SignUpState>

    @Binds
    fun bindSignInFormValidator(formValidator: SignInFormValidator): FormValidator<SignInState>
}