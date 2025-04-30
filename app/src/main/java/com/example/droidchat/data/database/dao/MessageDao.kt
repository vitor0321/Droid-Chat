package com.example.droidchat.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.droidchat.data.database.entity.MessageEntity

@Dao
internal interface MessageDao {
    @Query("SELECT * FROM messages WHERE receiver_id = :receiverId ORDER BY timestamp DESC")
    fun getPagedMessage(receiverId: Int): PagingSource<Int, MessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(messages: List<MessageEntity>)

    @Query("DELETE FROM messages WHERE receiver_id = :receiverId")
    suspend fun clearMessage(receiverId: Int)
}