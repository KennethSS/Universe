package com.solar.universe.binding.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.solar.universe.binding.viewbinding.ActivityViewBindingProperty

abstract class UniverseViewActivity<T: ViewBinding>(
    initialLayoutInflater: (LayoutInflater) -> T
) : AppCompatActivity() {

    protected val binding: T by activityViewBinding(initialLayoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    private fun activityViewBinding(
        bindingInitializer: (LayoutInflater) -> T
    ): ActivityViewBindingProperty<T> = ActivityViewBindingProperty(bindingInitializer)
}