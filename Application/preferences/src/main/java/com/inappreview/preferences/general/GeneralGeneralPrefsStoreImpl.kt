package com.inappreview.preferences.general

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.inappreview.preferences.Constants.STORE_NAME
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class GeneralGeneralPrefsStoreImpl  @Inject constructor(
    private val datastore: DataStore<Preferences>
): GeneralPrefsStore {

    companion object {
        val NIGHT_MODE_KEY = booleanPreferencesKey(STORE_NAME)
    }

    override fun isNightMode() = datastore.data.catch { exception ->
        if (exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { it[NIGHT_MODE_KEY] ?: false }

    override suspend fun toogleNightMode() {
        datastore.edit {
            it[NIGHT_MODE_KEY] = !(it[NIGHT_MODE_KEY] ?: false)
        }
    }

}