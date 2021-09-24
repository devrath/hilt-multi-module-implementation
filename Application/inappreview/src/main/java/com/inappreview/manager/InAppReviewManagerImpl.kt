package com.inappreview.manager

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.tasks.Task
import com.inappreview.preferences.BuildConfig
import com.inappreview.preferences.InAppReviewPrefsStoreImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class InAppReviewManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val reviewManager: ReviewManager,
    private val inAppReviewPreferences: InAppReviewPrefsStoreImpl
) : InAppReviewManager, CoroutineScope {

    override val coroutineContext: CoroutineContext get() = SupervisorJob() + Dispatchers.Main

    companion object {
        private const val KEY_REVIEW = "reviewFlow"
    }

    private var reviewInfo: ReviewInfo? = null

    init {
        launch {
            if (isEligibleForReview()) {
                reviewManager.requestReviewFlow().addOnCompleteListener {
                    if (it.isComplete && it.isSuccessful) {
                        reviewInfo = it.result
                    }
                }
            }
        }
    }

    override suspend fun isEligibleForReview(): Boolean {
        var isUserEligible = true
        inAppReviewPreferences.hasUserRatedApp().collect {
            isUserEligible = it
        }
        return isUserEligible
    }

    /*private fun enoughTimePassed(): Boolean {
        val rateLaterTimestamp = inAppReviewPreferences.getRateLaterTime()

        return kotlin.math.abs(rateLaterTimestamp - System.currentTimeMillis()) >=
                TimeUnit.DAYS.toMillis(14)
    }*/

    override fun startReview(activity: Activity) {
        reviewInfo?.let {
            reviewManager.launchReviewFlow(activity, it).addOnCompleteListener { reviewFlow ->
                onReviewFlowLaunchCompleted(reviewFlow)
            }
        }
    }

    private fun onReviewFlowLaunchCompleted(reviewFlow: Task<Void>) {
        if (reviewFlow.isSuccessful) {
            logSuccess()
        }
    }

    private fun logSuccess() {
        if (BuildConfig.DEBUG) {
            Log.d(KEY_REVIEW, "Review Complete!")
        }
    }


}