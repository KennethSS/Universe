package com.solar.universe.sample.recyclerview.holder

import android.view.View
import com.solar.universe.binding.recyclerview.UniverseViewHolder
import com.solar.universe.sample.databinding.ItemSomeBinding
import com.solar.universe.sample.recyclerview.model.SomeModel

class SampleViewHolder(
  private val binding: ItemSomeBinding
) : UniverseViewHolder(binding.root) {

  fun bind(item: SomeModel) {
    binding.tvTitle.text = item.title
  }
}