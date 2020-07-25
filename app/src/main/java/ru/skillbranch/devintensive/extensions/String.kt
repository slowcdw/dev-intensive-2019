package ru.skillbranch.devintensive.extensions

fun String.truncate(amount: Int = 16): String{
    var str = this.trim()
    if (str.length > amount) {
        str = str.substring(0, amount)
        str = str.trim() + "..."
    }
    return str
}