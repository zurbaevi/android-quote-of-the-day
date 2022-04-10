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

    fun create(x: Int): String {
        return when (x) {
            0 -> ENGLISH
            1 -> RUSSIAN
            else -> throw IllegalStateException()
        }
    }

}