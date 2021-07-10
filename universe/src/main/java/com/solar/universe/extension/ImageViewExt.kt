package com.solar.universe.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["load"])
fun ImageView.loadUrl(url: String?) {
    loadImage(url)
}

@BindingAdapter(value = ["loadCircle"])
fun ImageView.loadUrlCircle(url: String?) {
    loadImage(url)
}

private fun ImageView.loadImage(
    imageUrl: String?,
    @DrawableRes placeHolder: Int = 0,
    centerCrop: Boolean = false
) = Glide.with(this)
    .load(imageUrl)
    .thumbnail(0.1f)
    .placeholder(placeHolder)
    .also { if (centerCrop) it.apply(RequestOptions.circleCropTransform()) }
    .into(this)