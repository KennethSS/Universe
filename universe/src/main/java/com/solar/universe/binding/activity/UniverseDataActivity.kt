package com.solar.universe.binding.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class UniverseDataActivity<V : ViewDataBinding> constructor(
    @LayoutRes private val layoutId: Int
) : AppCompatActivity() {

    protected val binding: V by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView(this, layoutId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

    protected inline fun binding(block: V.() -> Unit): V {
        return binding.apply(block)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}