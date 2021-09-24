package com.inappreview.code

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inappreview.InAppReviewView
import com.inappreview.preferences.general.GeneralGeneralPrefsStoreImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val generalGeneralPrefsStoreImpl : GeneralGeneralPrefsStoreImpl
): ViewModel() {

    val dataSaved = MutableLiveData<String>()

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

    suspend fun saveToDataStore(dataToSave: String) {
        generalGeneralPrefsStoreImpl.saveString(dataToSave)
        dataSaved.value = generalGeneralPrefsStoreImpl.getString().toString()
    }

}