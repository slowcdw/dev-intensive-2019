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
    var exceptions = arrayOf(
        "enterprise",
        "features",
        "topics",
        "collections",
        "trending",
        "events",
        "marketplace",
        "pricing",
        "nonprofit",
        "customer",
        "stories",
        "security",
        "login",
        "join"
    )
    val regex = Regex("^(https://github.com/|https://www.github.com/|www.github.com/|github.com/)[^/\\s]+/?$")
    return regex.matches(this)||this.isBlank()||exceptions.contains(this)
}