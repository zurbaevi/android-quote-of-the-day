package dev.zurbaevi.common.util

class Language {
    companion object {
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
}