package com.example.droidchat.ui.feature.splash.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.data.network.model.NetworkException
import com.example.droidchat.domain.AuthRepository
import com.example.droidchat.ui.feature.splash.navigation.SplashAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _splashActionFlow = MutableSharedFlow<SplashAction>()
    val splashActionFlow = _splashActionFlow.asSharedFlow()

    fun checkSession() {
        viewModelScope.launch {
            val accessToken = authRepository.getAccessToken()
            if (accessToken.isNullOrBlank().not()) {
                _splashActionFlow.emit(SplashAction.NavigateToSignIn)
                return@launch
            }
            println("accessToken3: $accessToken")
            authRepository.authenticate(accessToken.toString()).fold(
                onSuccess = {
                    _splashActionFlow.emit(SplashAction.NavigateToSignIn)
                },
                onFailure = {
                    if (it is NetworkException.ApiException && it.statusCode == 401) {
                        authRepository.clearAccessToken()
                        _splashActionFlow.emit(SplashAction.UserNotAuthenticated)
                    } else {
                        _splashActionFlow.emit(SplashAction.ShowErrorDialog)
                    }
                }
            )
        }
    }
}