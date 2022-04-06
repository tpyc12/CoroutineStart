package com.myhome.android.coroutinestart

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.myhome.android.coroutinestart.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoad.setOnClickListener {
            lifecycleScope.launch {
                loadData()
            }
        }
    }

    private suspend fun loadData() {
        binding.pbLoading.isVisible = true
        binding.buttonLoad.isEnabled = false
        val city = loadCity()
        binding.tvCity.text = city
        val temp = loadTemp(city)
        binding.tvTemp.text = temp.toString()
        binding.pbLoading.isVisible = false
        binding.buttonLoad.isEnabled = true
    }

    private suspend fun loadTemp(city: String): Int {
        Toast.makeText(this, "Loading temperature for $city", Toast.LENGTH_SHORT).show()
        delay(5000)
        return 17
    }

    private suspend fun loadCity(): String {
        delay(5000)
        return "Moscow"
    }
}