package com.solar.universe.sample.recyclerview.holder

import com.solar.universe.binding.recyclerview.UniverseViewHolder
import com.solar.universe.sample.databinding.ItemOtherBinding
import com.solar.universe.sample.recyclerview.model.OtherModel

class OtherHolder(
  private val binding: ItemOtherBinding
) : UniverseViewHolder(binding.root) {

  fun bind(item: OtherModel) {
    binding.otherTitle.text = item.title
  }
}