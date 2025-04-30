package com.example.droidchat.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.droidchat.data.database.entity.MessageRemoteKeyEntity

@Dao
internal interface MessageRemoteKeyDao {
    @Query("SELECT * FROM message_remote_key WHERE receiver_id = :receiverId")
    fun getRemoteKey(receiverId: Int): MessageRemoteKeyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKey(remoteKey: MessageRemoteKeyEntity)

    @Query("DELETE FROM message_remote_key WHERE receiver_id = :receiverId")
    suspend fun clearRemoteKey(receiverId: Int)
}