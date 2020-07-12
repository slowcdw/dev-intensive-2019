package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance(){
        val user = User("1")
        val user2 = User("2", "Jon", "Snow")
        val user3 = User("3","Jon", "Silwerstown", null, lastVisit = Date(), isOnline = true)

        user.printMe()
        user2.printMe()
        user3.printMe()

        println("$user $user2 $user3")
    }

    @Test
    fun test_factory(){
        val user = User.makeUser("John Weck")
        val user2 = user.copy(id="2",lastName = "HZ", lastVisit = Date())
        val user3 = user.copy(id="3",lastName = "HZ1", lastVisit = Date().add(-5,TimeUnits.DAY))

//        val user2 = User.makeUser("")
//        val user3 = User.makeUser(" ")
//        val user4 = User.makeUser("John Wick")
        println("$user \n $user2")
        println("${user2.lastVisit?.format()}")
        print("${user3.lastVisit?.format()}")
    }

    @Test
    fun test_decomposition(){
        val user = User.makeUser("John Wick")
        fun getUserInfo() = user
        val (id, firstName, lastName) = getUserInfo()
        println("$id, $firstName, $lastName")
    }

    @Test
    fun test_abstract_factory(){
        val user = User.makeUser("John Wick")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")
        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())
    }
}
