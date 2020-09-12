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


fun String.validUrl(): Boolean{
    var exceptions = setOf(
        "enterprise",
        "features",
        "topics",
        "collections",
        "trending",
        "events",
        "marketplace",
        "pricing",
        "nonprofit",
        "customer-stories",
        "security",
        "login",
        "join"
    ).joinToString(separator = "|")
    val regex1 = Regex("^(https://github.com/|https://www.github.com/|www.github.com/|github.com/)($exceptions)/?$")
    val regex2 = Regex("^(https://github.com/|https://www.github.com/|www.github.com/|github.com/)[^/\\s]+/?$")
    return !regex1.matches(this)&&regex2.matches(this)||this.isBlank()
}
/*
fun String.validUrl(): Boolean {

    val exceptions = arrayOf(
        "enterprise",
        "features",
        "topics",
        "collections",
        "trending",
        "events",
        "marketplace",
        "pricing",
        "nonprofit",
        "customer-stories",
        "security",
        "login",
        "join"
    ).joinToString( "|\\b", "\\b" )

    val regex = Regex("^(?:https://)?(?:www.)?(?:github.com/)[^/|\\s]+((?:$exceptions)|(?<!$exceptions))(?:/)?$")

    return (this.isBlank() || regex.matches(this))
}*/
