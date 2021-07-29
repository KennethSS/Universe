package com.solar.universe.sample

import android.os.Bundle
import com.solar.universe.binding.activity.UniverseViewActivity
import com.solar.universe.sample.databinding.ActivityMainBinding
import com.solar.universe.sample.recyclerview.TestFragment

class MainActivity : UniverseViewActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvSample.text = "HI"

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_view, TestFragment(9))
            .commitNow()
    }
}