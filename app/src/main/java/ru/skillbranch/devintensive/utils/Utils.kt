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
}