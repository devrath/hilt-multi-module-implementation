package com.inappreview.preferences

import androidx.appcompat.app.AppCompatDelegate

/**
 * Prefs helper for user settings
 *
 * Data related to user settings preferences should be stored and accessed using [GeneralSettingsPrefs]
 */
class GeneralSettingsPrefs(private val prefs: PrefUtils) {

  companion object {
    private const val ONBOARDED_VIEWS = "onboarded_views"
  }

  init {
    prefs.init("settings")
  }

  /**
   * Clear preferences
   */
  fun clear() {
    prefs.clear()
  }

  /**
   * Save onboarded view
   *
   * @param view Onboarded view
   */
  fun saveOnboardedView(view: String) {
    //prefs.set(ONBOARDED_VIEWS, updatedOnboardedTypes).commit()
  }

  /**
   * Get onboarded views
   *
   * @return list of onboarded views
   */
  fun getOnboardedViews(): List<String> =
    prefs.get(ONBOARDED_VIEWS, "").split(",").map { it.trim() }
}
