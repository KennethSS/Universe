package com.solar.universe.extension

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.solar.universe.binding.viewbinding.FragmentViewBindingProperty
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> Fragment.viewBinding(): ReadOnlyProperty<Fragment, T> {
    return object : ReadOnlyProperty<Fragment, T> {
        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            (requireView().getTag(property.name.hashCode()) as? T)?.let { return it }
            val bind: (View) -> T = {
                T::class.java.getMethod("bind", View::class.java).invoke(null, it) as T
            }
            return bind(requireView()).also {
                requireView().setTag(property.name.hashCode(), it)
            }
        }
    }
}

fun <T : ViewBinding> Fragment.viewBinding(bind: (View) -> T): ReadOnlyProperty<Fragment, T> {
    return object : ReadOnlyProperty<Fragment, T> {
        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            (requireView().getTag(property.name.hashCode()) as? T)?.let { return it }
            return bind(requireView()).also {
                requireView().setTag(property.name.hashCode(), it)
            }
        }
    }
}

fun <T : ViewBinding> Fragment.fragmentViewBinding(
    viewBinder: (View) -> T
): FragmentViewBindingProperty<T> = FragmentViewBindingProperty(viewBinder)