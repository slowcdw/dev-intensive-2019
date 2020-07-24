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


    }
//        "${firstName?.let { it.substring(0, 1) }}${lastName?.let { it.substring(0, 1) }}".toUpperCase()

}

