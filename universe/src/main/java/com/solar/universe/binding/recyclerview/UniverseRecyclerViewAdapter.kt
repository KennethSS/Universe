package com.solar.universe.binding.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class UniverseRecyclerViewAdapter<T>(
  val list: ArrayList<T> = arrayListOf(),
) : RecyclerView.Adapter<UniverseViewHolder<T>>() {

  abstract fun holder(viewType: Int, view: View): UniverseViewHolder<T>

  fun add(item: T) {
    this.list.add(item)
    notifyItemInserted(this.list.count() - 1)
  }

  fun addAll(list: List<T>) {
    val previous = this.list.size
    this.list.addAll(list)
    notifyItemRangeInserted(previous, list.size)
  }

  fun update(position: Int, item: T) {
    list[position] = item
    notifyItemChanged(position, item)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniverseViewHolder<T> {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(viewType, parent, false)
    return holder(viewType, view)
  }

  override fun onBindViewHolder(holder: UniverseViewHolder<T>, position: Int) {
    val item = list[position]
  }

  override fun getItemCount(): Int = list.count()
}