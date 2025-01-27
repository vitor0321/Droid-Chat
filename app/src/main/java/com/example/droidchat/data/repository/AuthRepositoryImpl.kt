package com.example.droidchat.data.repository

import com.example.droidchat.data.NetWorkDataSource
import com.example.droidchat.data.di.IoDispatcher
import com.example.droidchat.data.manager.selfuser.SelfUserManager
import com.example.droidchat.data.network.model.AuthRequest
import com.example.droidchat.data.repository.mapper.CreateAccountMapper.toCreateAccountRequest
import com.example.droidchat.data.repository.mapper.ImageMapper.toImage
import com.example.droidchat.domain.AuthRepository
import com.example.droidchat.data.manager.token.TokenManager
import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.domain.model.Image
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val networkDataSource: NetWorkDataSource,
    private val tokenManager: TokenManager,
    private val selfUserManager: SelfUserManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    override suspend fun getAccessToken(): String? =
        withContext(ioDispatcher) {
            tokenManager.accessToken.firstOrNull()
        }

    override suspend fun clearAccessToken() =
        withContext(ioDispatcher) {
            tokenManager.clearAccessToken()
        }

    override suspend fun signUp(createAccount: CreateAccount): Result<Unit> =
        withContext(ioDispatcher) {
            runCatching {
                networkDataSource.signUp(request = createAccount.toCreateAccountRequest())
            }
        }

    override suspend fun signIn(email: String, password: String): Result<Unit> =
        withContext(ioDispatcher) {
            runCatching {
                val tokenResponse = networkDataSource.signIn(request = AuthRequest(email, password))
                tokenManager.saveAccessToken(tokenResponse.token)
            }
        }

    override suspend fun uploadProfilePicture(filePath: String): Result<Image> =
        withContext(ioDispatcher) {
            runCatching {
                networkDataSource.uploadProfilePicture(filePath = filePath).toImage()
            }
        }

    override suspend fun authenticate(token: String): Result<Unit> =
        withContext(ioDispatcher) {
            runCatching {
                val userResponse = networkDataSource.authenticate(token = token)
                selfUserManager.saveSelfUserData(
                    id = userResponse.id,
                    firstName = userResponse.firstName,
                    lastName = userResponse.lastName,
                    profilePictureUrl = userResponse.profilePictureUrl.toString(),
                    email = userResponse.email
                )
            }
        }
}