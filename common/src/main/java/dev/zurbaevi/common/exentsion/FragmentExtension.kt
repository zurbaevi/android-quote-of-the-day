package dev.zurbaevi.common.exentsion

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showShortSnackBar(message: String) {
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_SHORT).apply {
        animationMode = Snackbar.ANIMATION_MODE_SLIDE
        show()
    }
}

fun Fragment.showLongSnackBar(message: String) {
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG).apply {
        animationMode = Snackbar.ANIMATION_MODE_SLIDE
        show()
    }
}

