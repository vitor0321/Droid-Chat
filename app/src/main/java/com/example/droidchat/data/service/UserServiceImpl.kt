package com.example.droidchat.data.service

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.droidchat.data.di.IoDispatcher
import com.example.droidchat.data.mapper.UserResponseMapper.toUser
import com.example.droidchat.data.network.NetWorkDataSource
import com.example.droidchat.data.pagingSource.UserPagingSource
import com.example.droidchat.data.util.safeCallResult
import com.example.droidchat.domain.UserService
import com.example.droidchat.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UserServiceImpl @Inject constructor(
    private val netWorkDataSource: NetWorkDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UserService {

    override suspend fun getUser(userId: Int): Result<User> =
        safeCallResult(ioDispatcher) {
            netWorkDataSource.getUser(userId).toUser()
        }


    override fun getUsers(limit: Int): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = limit,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                UserPagingSource(
                    netWorkDataSource = netWorkDataSource,
                )
            }
        ).flow
    }
}