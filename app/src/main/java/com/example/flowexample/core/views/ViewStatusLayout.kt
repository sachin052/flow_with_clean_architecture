package com.example.flowexample.core.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.flowexample.R
import com.example.flowexample.databinding.DataStatusLayoutBinding


sealed class ViewStatus {
    object Loading : ViewStatus()

    object Success : ViewStatus()

    class Error(
        val message: String,
        @StringRes val buttonText: Int = R.string.retry,
        val action: (() -> Unit)? = null
    ) : ViewStatus()
}


class ViewStatusLayout(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private val binding = DataStatusLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        // 2 views added for loading and data
        if (childCount > 3) {
            throw Exception("Do not add more than one child to DataStatusLayout.")
        }
    }

    private fun showView(
        loading: Boolean = false,
        error: Boolean = false,
        success: Boolean = false
    ) {
        getChildAt(0).isVisible = loading
        getChildAt(1).isVisible = error
        getChildAt(2)?.isVisible = success
    }

    fun setStatus(status: ViewStatus?) {
        when (status) {
            ViewStatus.Loading -> showView(loading = true)
            ViewStatus.Success -> showView(success = true)
            is ViewStatus.Error -> {
                showView(error = true)
                binding.message.text = status.message
                binding.action.setText(status.buttonText)
                binding.action.isVisible = status.action != null
                binding.action.setOnClickListener {
                    status.action?.invoke()
                }
            }
            else -> showView() // Hide all views
        }
    }
}


@BindingAdapter("app:status")
fun ViewStatusLayout.setData(status: ViewStatus?) = setStatus(status)