package com.transistorapps.userstestapp.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.transistorapps.userstestapp.R

private const val CROSSFADE_DURATION = 500

fun ImageView.setCircleImage(uri: String, @DrawableRes placeholderRes: Int) {
    Glide.with(this)
        .load(uri)
        .apply(RequestOptions().apply(RequestOptions.circleCropTransform()))
        .placeholder(placeholderRes)
        .transition(DrawableTransitionOptions.withCrossFade(CROSSFADE_DURATION))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView.setImage(uri: String, @DrawableRes placeholderRes: Int) {
    Glide.with(this)
        .load(uri)
        .placeholder(placeholderRes)
        .transition(DrawableTransitionOptions.withCrossFade(CROSSFADE_DURATION))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun Context.getSnackBar(
    view: View,
    messageString: String,
    anchorView: View? = null,
): Snackbar? {
    return if (view.isAttachedToWindow) {
        val snackBar = Snackbar.make(view, messageString, Snackbar.LENGTH_INDEFINITE)
            .setAnchorView(anchorView)
            .addCallback(object : Snackbar.Callback() {
                override fun onShown(sb: Snackbar?) {
                    super.onShown(sb)
                    sb?.anchorView = null
                }
            })
        snackBar.view.background = ContextCompat.getDrawable(this, R.drawable.bg_loading_snackbar)
        snackBar.show()
        return snackBar
    } else null
}

fun Context.showSnackBar(
    view: View,
    messageString: String,
    anchorView: View? = null,
) {
    if (view.isAttachedToWindow) {
        val snackBar = Snackbar.make(view, messageString, Snackbar.LENGTH_LONG)
            .setAnchorView(anchorView)
            .addCallback(object : Snackbar.Callback() {
                override fun onShown(sb: Snackbar?) {
                    super.onShown(sb)
                    sb?.anchorView = null
                }
            })
        snackBar.view.background = ContextCompat.getDrawable(this, R.drawable.bg_error_snackbar)
        snackBar.show()
    }
}