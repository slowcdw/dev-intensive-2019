package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
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
    @Test
    fun test_initials(){
        println(Utils.toInitials("john" ,"doe")) //JD
        println(Utils.toInitials("John", null)) //J
        println(Utils.toInitials(null, null)) //null
        println(Utils.toInitials(" ", "")) //null
    }
    @Test
    fun test_transliteration(){
        println(Utils.transliteration("Женя Стереотипов")) //Zhenya Stereotipov
        println(Utils.transliteration("Amazing Петр","_")) //Amazing_Petr
    }
    @Test
    fun test_humDate(){
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff()) //через 2 минуты
        println(Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
        println(Date().add(400, TimeUnits.DAY).humanizeDiff()) //более чем через год
    }
    @Test
    fun test_builder(){
        val user = User.Builder().id("5")
            .firstName("Jon")
            .lastName("Connor")
            .avatar("")
            .rating(4)
            .respect(5)
            .lastVisit(Date())
            .isOnline(true)
            .build()
        print(user)
    }
    @Test
    fun test_trim(){
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate()) //Bender Bending R...
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15)) //Bender Bending...
        println("A     ".truncate(3)) //A
    }



}
