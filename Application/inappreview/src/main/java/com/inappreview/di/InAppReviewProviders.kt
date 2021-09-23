package com.inappreview.di

import android.content.Context
import android.content.SharedPreferences
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val KEY_IN_APP_REVIEW_PREFERENCES = "inAppReviewPreferences"

/**
 * Provides In App Review Android-based dependencies.
 * Module annotation represents that this is a module.
 * This module is mound to the singleton component
 * */
@Module
@InstallIn(SingletonComponent::class)
class InAppReviewProviders {

  /*
  Both the dependencies require context to create them and the hilt gets it from the
  application context annotation, Hilt connects them to the app class and then builds
  it using its context
  */

  /**
   * Provides In App Review Preferences.
   * Create the instance of shared preferences
   * */
  @Provides
  @Singleton
  fun provideInAppReviewPreferences(@ApplicationContext context: Context): SharedPreferences {
    return context.getSharedPreferences(KEY_IN_APP_REVIEW_PREFERENCES, Context.MODE_PRIVATE)
  }

  /**
   * Provides Review Manager
   * creates a instance of review manager
   * */
  @Provides
  @Singleton
  fun provideReviewManager(@ApplicationContext context: Context): ReviewManager {
    return ReviewManagerFactory.create(context)
  }
}