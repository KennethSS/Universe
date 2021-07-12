package com.solar.universe.binding.recyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class UniverseViewHolder<T>(
  val binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root)