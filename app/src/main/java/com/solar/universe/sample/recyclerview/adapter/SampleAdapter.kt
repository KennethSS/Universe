package com.solar.universe.sample.recyclerview.adapter

import android.view.View
import com.solar.universe.binding.recyclerview.UniverseItemModel
import com.solar.universe.binding.recyclerview.UniverseRecyclerViewAdapter
import com.solar.universe.binding.recyclerview.UniverseViewHolder
import com.solar.universe.sample.R
import com.solar.universe.sample.databinding.ItemOtherBinding
import com.solar.universe.sample.recyclerview.model.SomeModel
import com.solar.universe.sample.databinding.ItemSomeBinding
import com.solar.universe.sample.recyclerview.holder.OtherHolder
import com.solar.universe.sample.recyclerview.holder.SampleViewHolder

class SampleAdapter : UniverseRecyclerViewAdapter<UniverseItemModel>() {

  override fun getItemViewType(position: Int): Int = when(list[position]) {
    is SomeModel -> R.layout.item_some
    else -> R.layout.item_other
  }

  override fun holder(viewType: Int, view: View): UniverseViewHolder<UniverseItemModel> {
    return when(viewType) {
      R.layout.item_some -> SampleViewHolder(ItemSomeBinding.bind(view))
      else -> OtherHolder(ItemOtherBinding.bind(view))
    }
  }
}