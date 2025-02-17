package com.example.droidchat.data.service

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.droidchat.domain.UserService
import com.example.droidchat.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UserServiceImpl @Inject constructor(
    private val userPagingSource: PagingSource<Int, User>
) : UserService {

    override fun getUsers(limit: Int): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = limit,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { userPagingSource }
        ).flow
    }
}