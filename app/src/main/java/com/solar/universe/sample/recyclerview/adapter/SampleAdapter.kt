package com.solar.universe.sample.recyclerview.adapter

import android.view.View
import com.solar.universe.binding.recyclerview.UniverseItemModel
import com.solar.universe.binding.recyclerview.UniverseRecyclerViewAdapter
import com.solar.universe.binding.recyclerview.UniverseViewHolder
import com.solar.universe.sample.R
import com.solar.universe.sample.databinding.ItemOtherBinding
import com.solar.universe.sample.databinding.ItemSomeBinding
import com.solar.universe.sample.recyclerview.holder.OtherHolder
import com.solar.universe.sample.recyclerview.holder.SampleViewHolder
import com.solar.universe.sample.recyclerview.model.OtherModel
import com.solar.universe.sample.recyclerview.model.SomeModel

class SampleAdapter : UniverseRecyclerViewAdapter() {

  override fun getHolder(viewType: Int, view: View): UniverseViewHolder = when (viewType) {
    R.layout.item_some -> SampleViewHolder(ItemSomeBinding.bind(view))
    else -> OtherHolder(ItemOtherBinding.bind(view))
  }

  override fun bind(holder: UniverseViewHolder, item: UniverseItemModel) = when(holder){
    is SampleViewHolder -> holder.bind(item as SomeModel)
    is OtherHolder -> holder.bind(item as OtherModel)
    else -> { }
  }

  override fun getItemViewType(position: Int): Int = when(list[position]) {
    is SomeModel -> R.layout.item_some
    else -> R.layout.item_other
  }
}