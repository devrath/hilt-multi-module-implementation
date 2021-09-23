package com.inappreview

import android.app.Activity

/**
 * We use this Interface because like this way we can abstract the
 * actual manager
 */
interface InAppReviewManager {
    fun startReview(activity: Activity)
    fun isEligibleForReview(): Boolean
}