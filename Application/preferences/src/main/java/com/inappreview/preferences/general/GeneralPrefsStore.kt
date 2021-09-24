package com.inappreview.preferences.general

import kotlinx.coroutines.flow.Flow

interface GeneralPrefsStore {
    fun getString(): Flow<String>
    suspend fun saveString(strToSave:String)
}