package com.inappreview.code

import androidx.lifecycle.ViewModel
import com.inappreview.InAppReviewView
import dagger.hilt.android.lifecycle.HiltViewModel

class MainActivityViewModel : ViewModel() {

    private lateinit var inAppReviewView: InAppReviewView

    /**
     * Sets an interface that backs up the In App Review prompts.
     * */
    fun setInAppReviewView(inAppReviewView: InAppReviewView) {
        this.inAppReviewView = inAppReviewView
    }

    /**
     * Start In App Review
     * */
    fun startReview() {
        inAppReviewView.showReviewFlow()
    }

}