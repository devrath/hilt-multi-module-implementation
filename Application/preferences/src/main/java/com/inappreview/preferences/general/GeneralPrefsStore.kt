package com.inappreview.preferences.general

import kotlinx.coroutines.flow.Flow

interface GeneralPrefsStore {
    fun isNightMode(): Flow<Any>
    suspend fun toogleNightMode()
}