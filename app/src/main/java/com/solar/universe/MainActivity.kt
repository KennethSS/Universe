package com.solar.universe

import android.os.Bundle
import com.solar.universe.binding.UniverseViewActivity
import com.solar.universe.databinding.ActivityMainBinding

class MainActivity : UniverseViewActivity<ActivityMainBinding>() {

    init {
        bindingInitializer = ActivityMainBinding::inflate
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvSample.text = "HI"
    }
}