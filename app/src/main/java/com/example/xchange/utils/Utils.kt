package com.example.xchange.utils

class Utils {

    companion object {
        fun convertCurrencyToEmoji(textEmoji: String): String {
            var codepoints = intArrayOf(0xD83C, 0xDDF7, 0xD83C, 0xDDFA)
            if (textEmoji == "Гонконг") codepoints = intArrayOf(0xD83C, 0xDDED, 0xD83C, 0xDDF0)
            else if (textEmoji == "Япония") codepoints = intArrayOf(0xD83C, 0xDDEF, 0xD83C, 0xDDF5)
            else if (textEmoji == "США") codepoints = intArrayOf(0xD83C, 0xDDFA, 0xD83C, 0xDDF8)
            else if (textEmoji == "Южная Корея") codepoints = intArrayOf(0xD83C, 0xDDF0, 0xD83C, 0xDDF7)
            else if (textEmoji == "Европа") codepoints = intArrayOf(0xD83C, 0xDDEA, 0xD83C, 0xDDFA)
            else if (textEmoji == "Англия") codepoints = intArrayOf()
            else if (textEmoji == "Сингапур") codepoints = intArrayOf(0xD83C, 0xDDF8, 0xD83C, 0xDDEC)
            else if (textEmoji == "Индия") codepoints = intArrayOf(0xD83C, 0xDDEE, 0xD83C, 0xDDF3)
            else if (textEmoji == "Китай") codepoints = intArrayOf(0xD83C, 0xDDE8, 0xD83C, 0xDDF3)
            else if (textEmoji == "ОАЭ") codepoints = intArrayOf(0xD83C, 0xDDE6, 0xD83C, 0xDDEA)
            else if (textEmoji == "Австралия") codepoints = intArrayOf(0xD83C, 0xDDE6, 0xD83C, 0xDDFA)
            else if (textEmoji == "Казахстан") codepoints = intArrayOf(0xD83C, 0xDDF0, 0xD83C, 0xDDFF)
            else if (textEmoji == "Канада") codepoints = intArrayOf(0xD83C, 0xDDE8, 0xD83C, 0xDDE6)
            else if (textEmoji == "Вьетнам") codepoints = intArrayOf(0xD83C, 0xDDFB, 0xD83C, 0xDDF3)
            else if (textEmoji == "Великобритания") codepoints = intArrayOf(0xD83C, 0xDDEC, 0xD83C, 0xDDE7)
            else if (textEmoji == "Таиланд") codepoints = intArrayOf(0xD83C, 0xDDF9, 0xD83C, 0xDDED)
            else if (textEmoji == "Мексика") codepoints = intArrayOf(0xD83C, 0xDDF2, 0xD83C, 0xDDFD)
            else if (textEmoji == "Бразилия") codepoints = intArrayOf(0xD83C, 0xDDE7, 0xD83C, 0xDDF7)
            else if (textEmoji == "Макао") codepoints = intArrayOf(0xD83C, 0xDDF2, 0xD83C, 0xDDF4)
            else if (textEmoji == "Парагвай") codepoints = intArrayOf(0xD83C, 0xDDF5, 0xD83C, 0xDDFE)
            else if (textEmoji == "Саудовская Аравия") codepoints = intArrayOf(0xD83C, 0xDDF8, 0xD83C, 0xDDE6)
            else if (textEmoji == "Новая Зеландия") codepoints = intArrayOf(0xD83C, 0xDDF3, 0xD83C, 0xDDFF)
            else if (textEmoji == "Украина") codepoints = intArrayOf(0xD83C, 0xDDFA, 0xD83C, 0xDDE6)
            else if (textEmoji == "Киргизия") codepoints = intArrayOf(0xD83C, 0xDDF0, 0xD83C, 0xDDEC)
            else if (textEmoji == "Таджикистан") codepoints = intArrayOf(0xD83C, 0xDDF9, 0xD83C, 0xDDEF)
            else if (textEmoji == "Туркменистан") codepoints = intArrayOf(0xD83C, 0xDDF9, 0xD83C, 0xDDF2)
            else if (textEmoji == "Узбекистан") codepoints = intArrayOf(0xD83C, 0xDDFA, 0xD83C, 0xDDFF)
            else if (textEmoji == "Грузия") codepoints = intArrayOf(0xD83C, 0xDDEC, 0xD83C, 0xDDEA)
            return String(codepoints, 0, codepoints.size)
        }
    }

}