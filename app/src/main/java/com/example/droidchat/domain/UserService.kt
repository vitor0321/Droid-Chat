package com.example.droidchat.domain

import androidx.paging.PagingData
import com.example.droidchat.domain.model.User
import kotlinx.coroutines.flow.Flow

internal interface UserService {

    fun getUsers(limit: Int = 10): Flow<PagingData<User>>
}