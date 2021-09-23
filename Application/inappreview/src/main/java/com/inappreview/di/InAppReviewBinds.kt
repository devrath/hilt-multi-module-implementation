package com.inappreview.di

import com.inappreview.preferences.InAppReviewPreferences
import com.inappreview.preferences.InAppReviewPreferencesImpl
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
   * */
  @Binds
  @Singleton
  abstract fun bindInAppReviewPreferences(
    inAppReviewPreferencesImpl: InAppReviewPreferencesImpl
  ): InAppReviewPreferences
}