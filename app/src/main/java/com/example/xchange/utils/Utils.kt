package com.example.xchange.utils

class Utils {

    companion object {
        fun convertCurrencyToEmoji(textEmoji: String): String {
            val codepoints = intArrayOf(0xD83C, 0xDDF7, 0xD83C, 0xDDFA)
            return String(codepoints, 0, codepoints.size)
        }
    }

}