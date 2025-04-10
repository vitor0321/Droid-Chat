package com.example.droidchat.ui.feature.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.droidchat.domain.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class UsersViewModel @Inject constructor(
    private val userService: UserService
): ViewModel() {

    val usersFlow = userService.getUsers()
        .cachedIn(viewModelScope)
}