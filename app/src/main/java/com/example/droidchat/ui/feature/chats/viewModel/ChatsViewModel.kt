package com.example.droidchat.ui.feature.chats.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidchat.domain.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ChatsViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<ChatsListUiState>(ChatsListUiState.Loading)
    val state = _state.asStateFlow()

    init {
        getChats()
    }

    fun getChats() {
        viewModelScope.launch {
            _state.update { ChatsListUiState.Loading }
            chatRepository.getChats(offset = 0, limit = 10)
                .fold(
                    onSuccess = { chats -> _state.update { ChatsListUiState.Success(chats.toImmutableList()) } },
                    onFailure = { _state.update { ChatsListUiState.Error } }
                )
        }
    }
}