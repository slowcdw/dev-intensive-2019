package ru.skillbranch.devintensive.extensions

fun String.truncate(amount: Int = 16): String{
    var str = this.trim()
    if (str.length > amount) {
        str = str.substring(0, amount)
        str = str.trim() + "..."
    }
    return str
}

fun String.stripHtml(): String{
    var result = Regex("<[^>]*?>").replace(this, "")
    result = result.replace(Regex("\\s{2,}"), " ")
    result = result.replace(Regex("&[^;]*?;"), "")
    return result
}

fun String.validateUrl(): Boolean{
    val regex = Regex("https://github.com/|https://www.github.com/|www.github.com/|github.com/")
    regex.matches(this)
    return true
}