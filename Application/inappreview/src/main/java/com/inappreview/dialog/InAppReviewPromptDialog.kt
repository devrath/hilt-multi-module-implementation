package com.inappreview.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inappreview.manager.InAppReviewManager
import com.inappreview.inappreview.databinding.FragmentInAppReviewPromptBinding
import com.inappreview.preferences.InAppReviewPrefsStoreImpl
import com.inappreview.preferences.general.GeneralGeneralPrefsStoreImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.inappreview.inappreview.R
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InAppReviewPromptDialog : BottomSheetDialogFragment(), CoroutineScope {

  private val job = Job()
  override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.Main

  @Inject
  lateinit var preferences: InAppReviewPrefsStoreImpl

  @Inject
  lateinit var generalGeneralPrefsStoreImpl: GeneralGeneralPrefsStoreImpl

  @Inject
  lateinit var inAppReviewManager: InAppReviewManager

  private var binding: FragmentInAppReviewPromptBinding? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentInAppReviewPromptBinding.inflate(inflater, container, false)
    return binding?.root?.apply {
      setBackgroundResource(R.drawable.rounded_background);
    }
  }

  override fun getTheme(): Int {
    return R.style.CustomBottomSheetDialog
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initListeners()
    dialog?.setCanceledOnTouchOutside(false)
  }

  override fun onDestroy() {
    super.onDestroy()
    job.cancel()
  }

  private fun initListeners() {
    val binding = binding ?: return
    binding.leaveReview.setOnClickListener { lifecycleScope.launch { onLeaveReviewTapped() } }
    binding.reviewLater.setOnClickListener { lifecycleScope.launch { onRateLaterTapped() } }
  }

  private suspend fun onLeaveReviewTapped() {
    preferences.setUserRatedApp(true)
    inAppReviewManager.startReview(requireActivity())
    dismissAllowingStateLoss()
  }

  private suspend fun onRateLaterTapped() {
    preferences.setUserChosenRateLater(true)
    dismissAllowingStateLoss()
  }

  /**
   * If the user cancels the dialog, we process that as if they chose to "Rate Later".
   * */
  override fun onCancel(dialog: DialogInterface) {
    lifecycleScope.launch {
      preferences.apply {
       setUserChosenRateLater(true)
       setRateLater(getLaterTime())
      }
    }
    super.onCancel(dialog)
  }

  private fun getLaterTime(): Long {
    return System.currentTimeMillis() + TimeUnit.DAYS.toMillis(14)
  }

}