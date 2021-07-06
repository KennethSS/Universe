package com.solar.universe.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class UniverseFragment<B : ViewDataBinding> constructor(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    private var _binding: B? = null

    protected val binding: B
        get() = checkNotNull(_binding) {
            "Fragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
    }

    protected inline fun binding(block: B.() -> Unit): B {
        return binding.apply(block)
    }
}