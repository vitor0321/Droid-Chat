package com.example.droidchat.data.service

import com.example.droidchat.data.di.IoDispatcher
import com.example.droidchat.data.manager.SelfUserManager
import com.example.droidchat.data.manager.TokenManager
import com.example.droidchat.data.mapper.CreateAccountMapper.toCreateAccountRequest
import com.example.droidchat.data.mapper.ImageMapper.toImage
import com.example.droidchat.data.model.request.AuthRequest
import com.example.droidchat.data.network.NetWorkDataSource
import com.example.droidchat.data.util.safeCallResult
import com.example.droidchat.domain.AuthService
import com.example.droidchat.domain.model.CreateAccount
import com.example.droidchat.domain.model.Image
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AuthServiceImpl @Inject constructor(
    private val networkDataSource: NetWorkDataSource,
    private val tokenManager: TokenManager,
    private val selfUserManager: SelfUserManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthService {

    override suspend fun getAccessToken(): String? =
        withContext(ioDispatcher) {
            tokenManager.accessToken.firstOrNull()
        }

    override suspend fun clearAccessToken() =
        withContext(ioDispatcher) {
            tokenManager.clearAccessToken()
        }

    override suspend fun signUp(createAccount: CreateAccount): Result<Unit> =
        safeCallResult(ioDispatcher) {
            networkDataSource.signUp(request = createAccount.toCreateAccountRequest())
        }

    override suspend fun signIn(email: String, password: String): Result<Unit> =
        safeCallResult(ioDispatcher) {
            val tokenResponse = networkDataSource.signIn(request = AuthRequest(email, password))
                tokenManager.saveAccessToken(tokenResponse.token)
        }

    override suspend fun uploadProfilePicture(filePath: String): Result<Image> =
        safeCallResult(ioDispatcher) {
            networkDataSource.uploadProfilePicture(filePath = filePath).toImage()
        }

    override suspend fun authenticate(): Result<Unit> =
        safeCallResult(ioDispatcher) {
            val userResponse = networkDataSource.authenticate()
                selfUserManager.saveSelfUserData(
                    id = userResponse.id,
                    firstName = userResponse.firstName,
                    lastName = userResponse.lastName,
                    profilePictureUrl = userResponse.profilePictureUrl.toString(),
                    email = userResponse.email
                )
            }
}