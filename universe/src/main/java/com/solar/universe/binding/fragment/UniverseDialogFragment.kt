package com.solar.universe.binding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager

abstract class UniverseDialogFragment<B: ViewDataBinding> constructor(
    @LayoutRes private val layoutId: Int
): AppCompatDialogFragment() {

    private var _binding: B? = null

    protected val binding: B
        get() = checkNotNull(_binding) {
            "Fragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
        }

    fun showDialog(fragmentManager: FragmentManager?, tag: String) {
        fragmentManager?.let {
            show(it, tag)
        }
    }

    fun dismissDialog() {
        dismiss()
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
}