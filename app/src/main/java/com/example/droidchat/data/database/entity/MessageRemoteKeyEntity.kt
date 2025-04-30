package com.example.droidchat.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_remote_key")
internal data class MessageRemoteKeyEntity(
    @PrimaryKey
    @ColumnInfo("receiver_id")
    val receiverId: Int,
    @ColumnInfo("next_offset")
    val nextOffset: Int?,
)