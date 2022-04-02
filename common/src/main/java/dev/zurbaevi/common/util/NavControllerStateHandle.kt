package dev.zurbaevi.common.util

import androidx.navigation.NavController

class NavControllerStateHandle<T>(private val navController: NavController) {

    fun savedPreviousBackStackEntry(key: String, value: T) {
        navController.previousBackStackEntry?.savedStateHandle?.set(key, value)
    }

    fun getCurrentBackStackEntry(key: String): T? {
        return navController.currentBackStackEntry?.savedStateHandle?.get(key)
    }

}