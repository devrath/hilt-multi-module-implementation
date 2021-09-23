package com.inappreview.code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.inappreview.manager.InAppReviewManager
import com.inappreview.InAppReviewView
import com.inappreview.code.databinding.ActivityMainBinding
import com.inappreview.dialog.InAppReviewPromptDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), InAppReviewView {

    @Inject
    lateinit var inAppReviewManager: InAppReviewManager

    private val viewModel : MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setInAppReviewView(this)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.startReviewProcess.setOnClickListener {
            viewModel.startReview()
        }
    }

    override fun showReviewFlow() {
        val dialog = InAppReviewPromptDialog()
        dialog.show(supportFragmentManager, null)
    }

}