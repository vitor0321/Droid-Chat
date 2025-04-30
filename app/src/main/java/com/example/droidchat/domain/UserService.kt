package com.example.droidchat.domain

import androidx.paging.PagingData
import com.example.droidchat.domain.model.User
import kotlinx.coroutines.flow.Flow

internal interface UserService {

    suspend fun getUser(userId: Int): Result<User>

    fun getUsers(limit: Int = 10): Flow<PagingData<User>>
}