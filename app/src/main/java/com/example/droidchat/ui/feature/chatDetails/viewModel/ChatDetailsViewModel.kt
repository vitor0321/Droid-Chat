package com.example.droidchat.ui.feature.chatDetails.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.example.droidchat.domain.ChatService
import com.example.droidchat.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ChatDetailsViewModel @Inject constructor(
    private val chatService: ChatService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val chatDetailsRoute = savedStateHandle.toRoute<Route.ChatDetailRoute>()

    /**
     * Armazena a referência para a coroutine de envio de mensagens.
     * Assim podemos, controlar o ciclo de vida das coroutines no ViewModel.
     * para não chamar múltiplas operações de envio simultâneas.
     * 1. Cancelar operações de envio em andamento quando uma nova mensagem é enviada
     * 2. Evitar múltiplas operações de envio simultâneas
     * 3. Gerenciar corretamente o ciclo de vida das coroutines no ViewModel
     *
     * O valor null indica que não há uma operação de envio em andamento.
     */
    var sendMessageJob: Job?= null

    var messageText by mutableStateOf("")
    private set

    val pagingChatMessages = chatService.getPageMessages(
        receiverId = chatDetailsRoute.userId
    ).cachedIn(viewModelScope)

    fun onMessageChange(message: String) {
        messageText = message
    }

    fun sendMessage() {
        if (messageText.isBlank()) return
        sendMessageJob?.cancel()
        sendMessageJob = viewModelScope.launch {
            chatService.sendMessage(
                receiverId = chatDetailsRoute.userId,
                message = messageText
            )
            messageText = ""
        }
    }
}