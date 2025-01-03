package com.example.droidchat.data.repository

import com.example.droidchat.data.di.IoDispatcher
import com.example.droidchat.data.network.model.AuthRequest
import com.example.droidchat.data.repository.mapper.CreateAccountMapper.toCreateAccountRequest
import com.example.droidchat.data.repository.mapper.ImageMapper.toImage
import com.example.droidchat.domain.AuthRepository
import com.example.droidchat.domain.NetWorkDataSource
import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.domain.model.Image
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetWorkDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun signUp(createAccount: CreateAccount): Result<Unit> =
        withContext(ioDispatcher) {
            runCatching {
                networkDataSource.signUp(request = createAccount.toCreateAccountRequest())
            }
        }

    override suspend fun signIn(userName: String, password: String): Result<Unit> =
        runCatching {
            networkDataSource.signIn(request = AuthRequest(userName, password))
        }

    override suspend fun uploadProfilePicture(filePath: String): Result<Image> =
        withContext(ioDispatcher) {
            runCatching {
                networkDataSource.uploadProfilePicture(filePath = filePath).toImage()
            }
        }
}