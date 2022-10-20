package com.solanacode.workmanagerchap6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.solanacode.workmanagerchap6.databinding.ActivityBlurBinding
import com.solanacode.workmanagerchap6.viewmodel.BlurVIewModel
import com.solanacode.workmanagerchap6.viewmodel.BlurViewModelFactory

class BlurActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlurBinding
    private val viewModel: BlurVIewModel by viewModels { BlurViewModelFactory(application) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBlur.setOnClickListener {
            viewModel.applyBlur(1)
        }



    }
    private val blurLevel: Int get() = when (binding.radioBlurGroup.checkedRadioButtonId) {
        R.id.radio_blur_lv_1 -> 1
        R.id.radio_blur_lv_2 -> 2
        R.id.radio_blur_lv_3 -> 3 else -> 1
    }


}