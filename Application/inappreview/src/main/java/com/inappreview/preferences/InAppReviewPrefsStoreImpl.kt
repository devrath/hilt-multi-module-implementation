package com.inappreview.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.inappreview.preferences.Constants.STORE_NAME
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class InAppReviewPrefsStoreImpl @Inject constructor(
    private val datastore: DataStore<Preferences>
) : InAppReviewPrefsStore {

    companion object {
        val hasRatedApp = booleanPreferencesKey(STORE_NAME)
        val rateLater = booleanPreferencesKey(STORE_NAME)
        val rateLaterTime = longPreferencesKey(STORE_NAME)
    }

    override fun hasUserRatedApp() = datastore.data.catch { exception ->
        if (exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { it[hasRatedApp] ?: false }

    override suspend fun setUserRatedApp(hasRated: Boolean) {
        datastore.edit {
            it[hasRatedApp] = hasRated
        }
    }

    override fun hasUserChosenRateLater() = datastore.data.catch { exception ->
        if (exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { it[rateLater] ?: false }

    override suspend fun setUserChosenRateLater(hasChosenRateLater: Boolean) {
        datastore.edit {
            it[rateLater] = hasChosenRateLater
        }
    }

    override fun getRateLaterTime() = datastore.data.catch { exception ->
        if (exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { it[rateLaterTime] ?: 0 }

    override suspend fun setRateLater(time: Long) {
        datastore.edit {
            it[rateLaterTime] = time
        }
    }

}