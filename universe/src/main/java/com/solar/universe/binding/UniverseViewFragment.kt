package com.solar.universe.binding

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.solar.universe.extension.fragmentViewBinding

// Todo onDestroyView binding 메모리 누수 케이스 처리
abstract class UniverseViewFragment<T : ViewBinding>(
    @LayoutRes layoutResId: Int,
    initialLayoutInflater: (View) -> T
) : Fragment(layoutResId) {

    private val _binding: T by fragmentViewBinding(initialLayoutInflater)

    protected val binding: T
        get() = _binding

    abstract fun onViewCreated(bind: T, savedInstanceState: Bundle?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(binding, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}