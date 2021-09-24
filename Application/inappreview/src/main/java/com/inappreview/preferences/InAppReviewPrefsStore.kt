package com.inappreview.preferences

import kotlinx.coroutines.flow.Flow

interface InAppReviewPrefsStore {
    fun hasUserRatedApp(): Flow<Boolean>
    suspend fun setUserRatedApp(hasRated: Boolean)
    fun hasUserChosenRateLater(): Flow<Boolean>
    suspend fun setUserChosenRateLater(hasChosenRateLater: Boolean)
    fun getRateLaterTime(): Flow<Long>
    suspend fun setRateLater(time: Long)
}