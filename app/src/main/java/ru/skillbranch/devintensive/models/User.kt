package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false
){
    constructor(id : String, firstName: String?, lastName: String?) : this(id, firstName, lastName, null)
    constructor(id: String) : this(id, "Jon", "Silver")

    init {
        println("It`s Alive!!! \nHis name is $firstName $lastName")
    }

    fun printMe():Unit{
        println("""
            id : $id 
            firstName: $firstName  
            lastName : $lastName 
            avatar : $avatar 
            rating : $rating 
            respect : $respect 
            lastVisit: $lastVisit  
            isOnline : $isOnline 
        """.trimIndent())
    }

    companion object Factory{
        private var lastId : Int = -1
        fun makeUser(fullName:String?) : User{
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }

    }
    class Builder(){
        private var user: User? = null

        fun id(id:String): Builder{
            user = User(id)
            return this
        }
        fun firstName(firstName:String): Builder {
            user?.firstName = firstName
            return this
        }
        fun lastName(lastName:String): Builder {
            user?.lastName = lastName
            return this
        }
        fun avatar(avatar:String): Builder {
            user?.avatar = avatar
            return this
        }
        fun rating(rating:Int): Builder {
            user?.rating = rating
            return this
        }
        fun respect(respect:Int): Builder {
            user?.respect = respect
            return this
        }
        fun lastVisit(lastVisit:Date): Builder {
            user?.lastVisit = lastVisit
            return this
        }
        fun isOnline(isOnline:Boolean): Builder {
            user?.isOnline = isOnline
            return this
        }
        fun build(): User? {
            return user
        }

    }

}
