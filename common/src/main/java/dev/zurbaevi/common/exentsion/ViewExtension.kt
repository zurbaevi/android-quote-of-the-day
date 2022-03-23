package dev.zurbaevi.common.exentsion

import android.os.SystemClock
import android.view.View
import android.widget.ImageView

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.setOnClickListenerWithDebounce(debounceTime: Long, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun ImageView.setImageIfResource(
    isFavorite: Boolean,
    firstImageResource: Int,
    secondImageResource: Int
) {
    return when (isFavorite) {
        true -> this.setImageResource(firstImageResource)
        false -> this.setImageResource(secondImageResource)
    }
}