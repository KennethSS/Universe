package com.solar.universe.sample

import android.os.Bundle
import com.solar.universe.binding.UniverseViewActivity
import com.solar.universe.sample.databinding.ActivityMainBinding

class MainActivity : UniverseViewActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvSample.text = "HI"
    }
}