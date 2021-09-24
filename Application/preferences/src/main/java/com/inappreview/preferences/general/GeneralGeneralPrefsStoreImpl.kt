package com.inappreview.preferences.general

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.inappreview.preferences.Constants.STORE_NAME
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class GeneralGeneralPrefsStoreImpl  @Inject constructor(
    private val datastore: DataStore<Preferences>
): GeneralPrefsStore {

    companion object {
        val strKeyToSave = stringPreferencesKey(STORE_NAME)
    }

    override fun getString() = datastore.data.catch { exception ->
        if (exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { it[strKeyToSave] ?: "" }

    override suspend fun saveString(strToSave:String) {
        datastore.edit {
            it[strKeyToSave] = strToSave
        }
    }

}