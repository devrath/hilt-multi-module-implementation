package com.inappreview.preferences.di

import com.inappreview.preferences.general.GeneralPrefsStore
import com.inappreview.preferences.general.GeneralGeneralPrefsStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StoreModule {

 @Binds
  abstract fun bindPrefsStore(generalPrefsStoreImpl: GeneralGeneralPrefsStoreImpl): GeneralPrefsStore

}