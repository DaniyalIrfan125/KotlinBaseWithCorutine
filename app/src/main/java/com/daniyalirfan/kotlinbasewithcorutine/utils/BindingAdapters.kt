package com.daniyalirfan.kotlinbasewithcorutine.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("error")
    fun setError(textInputLayout: TextInputLayout, error: String?) {
        textInputLayout.error = error
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}