package dev.zurbaevi.common.util

object Language {
    const val ENGLISH = "en"
    const val RUSSIAN = "ru"

    fun create(x: String): String {
        return when (x) {
            "en" -> RUSSIAN
            "ru" -> ENGLISH
            else -> throw IllegalStateException()
        }
    }
}