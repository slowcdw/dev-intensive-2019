package ru.skillbranch.devintensive.utils
object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val firstName : String?
        val lastName : String?
        if (fullName?.trim().equals("")){
            firstName = null
            lastName = null
        }else{
            val parts : List<String>? = fullName?.split(" ")
            firstName = parts?.getOrNull(0)
            lastName = parts?.getOrNull(1)
        }
        return Pair(firstName, lastName)
    }

    fun toInitials(firstName:String?, lastName:String?): String? {
        var firstI: String? = null
        var lastI: String? = null
        if (!firstName.isNullOrBlank()){
            firstI = firstName.substring(0, 1).toUpperCase()
        }
        if (!lastName.isNullOrBlank()){
            lastI = lastName.substring(0, 1).toUpperCase()
        }
        return if (firstI != null && lastI != null) firstI+lastI
        else if (firstI != null && lastI == null) firstI
        else if (firstI == null && lastI != null) lastI
        else null
//        "${firstName?.let { it.substring(0, 1) }}${lastName?.let { it.substring(0, 1) }}".toUpperCase()
    }
    fun transliteration(payload: String, divider: String = " "): String{
        val dictionary = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
        )
        var newStr: String? = ""
        val list = payload.split(" ")
        for (lst in list){
            for (chr in lst.indices){
//                println(dictionary.getOrElse(lst[chr].toString()) {lst[chr].toString()})
                newStr += if (lst[chr].isUpperCase()){
                    val tmpStr = dictionary.getOrElse(lst[chr].toString().toLowerCase()) {lst[chr].toString()}
                    tmpStr[0].toString().toUpperCase() + tmpStr.substring(1)
                }else{
                    dictionary.getOrElse(lst[chr].toString()) {lst[chr].toString()}
                }
            }
            newStr += divider
        }
        return newStr!!.substring(0, newStr.length - divider.length)
    }
/*fun transliteration(payload: String, divider: String = " "): String {
    val fullName: List<String> = payload.split(" ").filter { a -> a.isNotBlank() }
    var res = ""
    for (word: String in fullName) {
        var sb = ""
        val charArray = word.toCharArray()
        for (ch: Char in charArray.iterator()) {
            val char = when (ch) {
                'а' -> "a"
                'б' -> "b"
                'в' -> "v"
                'г' -> "g"
                'д' -> "d"
                'е' -> "e"
                'ё' -> "e"
                'ж' -> "zh"
                'з' -> "z"
                'и' -> "i"
                'й' -> "i"
                'к' -> "k"
                'л' -> "l"
                'м' -> "m"
                'н' -> "n"
                'о' -> "o"
                'п' -> "p"
                'р' -> "r"
                'с' -> "s"
                'т' -> "t"
                'у' -> "u"
                'ф' -> "f"
                'х' -> "h"
                'ц' -> "c"
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh'"
                'ъ' -> ""
                'ы' -> "i"
                'ь' -> ""
                'э' -> "e"
                'ю' -> "yu"
                'я' -> "ya"

                'А' -> "A"
                'Б' -> "B"
                'В' -> "V"
                'Г' -> "G"
                'Д' -> "D"
                'Е' -> "E"
                'Ё' -> "E"
                'Ж' -> "Zh"
                'З' -> "Z"
                'И' -> "I"
                'Й' -> "I"
                'К' -> "K"
                'Л' -> "L"
                'М' -> "M"
                'Н' -> "N"
                'О' -> "O"
                'П' -> "P"
                'Р' -> "R"
                'С' -> "S"
                'Т' -> "T"
                'У' -> "U"
                'Ф' -> "F"
                'Х' -> "H"
                'Ц' -> "C"
                'Ч' -> "Ch"
                'Ш' -> "Sh"
                'Щ' -> "Sh'"
                'Ъ' -> ""
                'Ы' -> "I"
                'Ь' -> ""
                'Э' -> "E"
                'Ю' -> "Yu"
                'Я' -> "Ya"
                else -> ch.toString()
            }
            sb = "$sb$char"
        }
        res = when {
            res.isNotBlank() -> "$res$divider$sb"
            else -> "$res$sb"
        }
        *//*if (res.isNotEmpty() || (res != " ")) res = "$res$divider$sb"
        else res = "$res$sb"*//*
    }

    return res
}*/
}

