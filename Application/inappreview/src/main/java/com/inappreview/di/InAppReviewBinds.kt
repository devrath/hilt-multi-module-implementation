package com.inappreview.di

import com.inappreview.manager.InAppReviewManager
import com.inappreview.manager.InAppReviewManagerImpl
import com.inappreview.preferences.InAppReviewPrefsStore
import com.inappreview.preferences.InAppReviewPrefsStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides dependencies required for In App Review flow.
 * */
@Module
@InstallIn(SingletonComponent::class)
abstract class InAppReviewBinds {

  /**
   * Provides Preferences wrapper.
   * We pass the implementation as the parameter and return the interface of that
   * */
  @Binds
  @Singleton
  abstract fun bindInAppReviewPreferences(
    inAppReviewPrefsStoreImpl: InAppReviewPrefsStoreImpl
  ): InAppReviewPrefsStore

  /**
   * Provides In App Review flow wrapper.
   * */
  @Binds
  @Singleton
  abstract fun bindInAppReviewManager(
    inAppReviewManagerImpl: InAppReviewManagerImpl
  ): InAppReviewManager
}