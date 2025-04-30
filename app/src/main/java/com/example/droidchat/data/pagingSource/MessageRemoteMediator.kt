package com.example.droidchat.data.pagingSource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.droidchat.data.database.DatabaseDataSource
import com.example.droidchat.data.database.DroidChatDatabase
import com.example.droidchat.data.database.entity.MessageEntity
import com.example.droidchat.data.database.entity.MessageRemoteKeyEntity
import com.example.droidchat.data.mapper.MessageResponseMapper.toEntityModel
import com.example.droidchat.data.model.request.PaginationParams
import com.example.droidchat.data.network.NetWorkDataSource

@OptIn(ExperimentalPagingApi::class)
internal class MessageRemoteMediator(
    private val netWorkDataSource: NetWorkDataSource,
    private val databaseDataSource: DatabaseDataSource,
    private val receiverId: Int,
    private val database: DroidChatDatabase,
) : RemoteMediator<Int, MessageEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MessageEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = databaseDataSource.getMessageRemoteKey(receiverId)
                    remoteKey?.nextOffset ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val limit = state.config.pageSize
            val paginationParams = PaginationParams(
                offset = offset.toString(),
                limit = limit.toString()
            )

            val response = netWorkDataSource.getMessages(
                receiverId = receiverId,
                paginationParams = paginationParams
            )

            val entities = response.toEntityModel()

            database.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    databaseDataSource.clearMessage(receiverId)
                    databaseDataSource.clearMessageRemoteKey(receiverId)
                }

                databaseDataSource.insertMessageRemoteKey(
                    remoteKey = MessageRemoteKeyEntity(
                        receiverId = receiverId,
                        nextOffset = if (response.hasMore) offset + limit else null,
                    )
                )

                databaseDataSource.insertMessage(messages = entities)
            }

            MediatorResult.Success(endOfPaginationReached = !response.hasMore)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}