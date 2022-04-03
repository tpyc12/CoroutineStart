package com.myhome.android.coroutinestart

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.myhome.android.coroutinestart.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoad.setOnClickListener {
            loadDate()
        }
    }

    private fun loadDate() {
        binding.pbLoading.isVisible = true
        binding.buttonLoad.isEnabled = false
        loadCity { city ->
            binding.tvCity.text = city
            loadTemp(city) { temp ->
                binding.tvTemp.text = temp.toString()
                binding.pbLoading.isVisible = false
                binding.buttonLoad.isEnabled = true
            }
        }
    }

    private fun loadTemp(city: String, callback: (Int) -> Unit) {
        thread {
            Toast.makeText(this, "Loading temperature for $city", Toast.LENGTH_SHORT).show()
            Thread.sleep(5000)
            callback.invoke(17)
        }
    }

    private fun loadCity(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            callback.invoke("Moscow")
        }
    }
}