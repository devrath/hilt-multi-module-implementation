package com.inappreview.preferences.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val STORE_NAME = "learning_data_store"

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

}