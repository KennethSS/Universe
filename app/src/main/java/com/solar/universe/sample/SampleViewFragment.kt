package com.solar.universe.sample

import android.os.Bundle
import com.solar.universe.binding.UniverseViewFragment
import com.solar.universe.sample.databinding.FragmentMainBinding

class SampleViewFragment : UniverseViewFragment<FragmentMainBinding>(R.layout.fragment_main, FragmentMainBinding::bind) {
    override fun onViewCreated(bind: FragmentMainBinding, savedInstanceState: Bundle?) {

    }
}