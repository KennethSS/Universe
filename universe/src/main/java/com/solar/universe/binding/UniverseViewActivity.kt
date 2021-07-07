package com.solar.universe.binding

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.solar.universe.binding.viewbinding.ActivityViewBindingProperty

abstract class UniverseViewActivity<T: ViewBinding> : AppCompatActivity() {

    val binding: T by activityViewBinding(bindingInitializer)

    lateinit var bindingInitializer: (LayoutInflater) -> T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun activityViewBinding(
        bindingInitializer: (LayoutInflater) -> T
    ): ActivityViewBindingProperty<T> = ActivityViewBindingProperty(bindingInitializer)
}