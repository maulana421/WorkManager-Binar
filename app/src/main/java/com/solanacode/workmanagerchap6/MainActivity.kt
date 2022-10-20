package com.solanacode.workmanagerchap6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.solanacode.workmanagerchap6.databinding.ActivityMainBinding
import com.solanacode.workmanagerchap6.viewmodel.BlurVIewModel
import com.solanacode.workmanagerchap6.viewmodel.BlurViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: BlurVIewModel by viewModels { BlurViewModelFactory(application) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}