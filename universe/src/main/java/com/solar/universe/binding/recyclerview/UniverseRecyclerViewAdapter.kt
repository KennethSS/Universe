package com.solar.universe.binding.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class UniverseRecyclerViewAdapter(
  val list: ArrayList<UniverseItemModel> = arrayListOf(),
) : RecyclerView.Adapter<UniverseViewHolder>() {

  abstract fun getHolder(viewType: Int, view: View): UniverseViewHolder

  abstract fun bind(holder: UniverseViewHolder, item: UniverseItemModel)

  fun add(item: UniverseItemModel) {
    this.list.add(item)
    notifyItemInserted(this.list.count() - 1)
  }

  fun addAll(list: List<UniverseItemModel>) {
    val previous = this.list.size
    this.list.addAll(list)
    notifyItemRangeInserted(previous, list.size)
  }

  fun update(position: Int, item: UniverseItemModel) {
    list[position] = item
    notifyItemChanged(position, item)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniverseViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(viewType, parent, false)
    return getHolder(viewType, view)
  }

  override fun onBindViewHolder(holder: UniverseViewHolder, position: Int) {
    bind(holder, list[position])
  }

  override fun getItemCount(): Int = list.count()
}