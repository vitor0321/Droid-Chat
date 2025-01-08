package com.example.droidchat.data.datastore

import android.content.Context
import androidx.datastore.dataStore

internal val Context.selfUserStore by dataStore(
    fileName = "self_user.pb",
    serializer = SelfUserSerializer
)