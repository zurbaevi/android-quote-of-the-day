package dev.zurbaevi.common.util


enum class Language(val language: String) {
    ENGLISH("en"),
    RUSSIAN("ru");

    companion object {
        fun create(x: String): Language {
            return when (x) {
                "en" -> ENGLISH
                "ru" -> RUSSIAN
                else -> throw IllegalStateException()
            }
        }
    }
}