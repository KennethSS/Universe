package com.solar.universe.sample

import android.os.Bundle
import com.solar.universe.binding.activity.UniverseViewActivity
import com.solar.universe.sample.databinding.ActivitySampleBinding
import com.solar.universe.sample.recyclerview.adapter.SampleAdapter
import com.solar.universe.sample.recyclerview.model.OtherModel
import com.solar.universe.sample.recyclerview.model.SomeModel

class SampleViewActivity : UniverseViewActivity<ActivitySampleBinding>(ActivitySampleBinding::inflate){

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.listView.adapter = SampleAdapter().apply {
      addAll(listOf(
        SomeModel("Some"),
        OtherModel("Other"),
      ))
    }
  }
}