package dev.zurbaevi.common.util


//enum class Language(val language: String) {
//    ENGLISH("en"),
//    RUSSIAN("ru");
//
//    companion object {
//        fun create(x: String): Language {
//            return when (x) {
//                "en" -> ENGLISH
//                "ru" -> RUSSIAN
//                else -> throw IllegalStateException()
//            }
//        }
//    }
//}

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