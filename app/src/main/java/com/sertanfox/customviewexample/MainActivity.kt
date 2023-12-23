package com.sertanfox.customviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.sertanfox.customviewexample.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButton()

        binding.customView.setOnClickListener {
            binding.customView.setBackgroundResource(R.drawable.btn_empty_stroke)
            binding.customView.showLoader()
            job = lifecycleScope.launch {
                delay(3000)
                binding.customView.setText("SUCCESS")
                binding.customView. hideLoader()
                binding.customView.setBackgroundResource(R.drawable.btn_enable_bg)
                job.cancel()
            }
        }

    }

    private fun setButton() {
        binding.customView.setBackgroundResource(R.drawable.btn_empty_stroke)
        binding.customView.hideLoader()
    }
}