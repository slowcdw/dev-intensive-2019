package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}
fun Date.shortFormat(): String {
    val pattern = if(this.isSameDay(Date())) "HH:mm" else "dd.MM.yy"
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return  dateFormat.format(this)
}

fun Date.isSameDay(date: Date): Boolean {
    val day1 = this.time / DAY
    val day2 = date.time / DAY
    return  day1 == day2
}
fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time

    return this
}

enum class TimeUnits{
    SECOND {
        override fun plural(value: Int): String {
            return when{
                value % 10 == 1 -> "$value секунду"
                value % 10 in 2..4 -> "$value секунды"
                else -> "$value секунд"
            }
        }
    },
    MINUTE {
        override fun plural(value: Int): String {
            return when{
                value % 10 == 1 -> "$value минуту"
                value % 10 in 2..4 -> "$value минуты"
                else -> "$value минут"
            }
        }
    },
    HOUR {
        override fun plural(value: Int): String {
            return when{
                value % 10 == 1 -> "$value час"
                value % 10 in 2..4 -> "$value часа"
                else -> "$value часов"
            }
        }
    },
    DAY {
        override fun plural(value: Int): String {
            return when{
                value % 10 == 1 -> "$value день"
                value % 10 in 2..4 -> "$value дня"
                else -> "$value дней"
            }
        }
    };
    abstract fun plural(value:Int):String
}

fun Date.humanizeDiff(): String{
    val diff = Date().time - this.time
    return when {
        diff in 0 until SECOND -> "только что"
        diff in SECOND until SECOND*45 ->  "несколько секунд назад"
        diff in SECOND*45 until SECOND*75 ->  "минуту назад"
        diff in SECOND*75 until MINUTE*45 -> "${TimeUnits.MINUTE.plural((diff/ MINUTE).toInt())} назад"
        diff in MINUTE*45 until MINUTE*75 -> "час назад"
        diff in MINUTE*75 until HOUR*22 -> "${TimeUnits.HOUR.plural((diff/ HOUR).toInt())} назад"
        diff in HOUR*22 until HOUR*26 -> "день назад"
        diff in HOUR*26..DAY*360 -> "${TimeUnits.DAY.plural((diff/ DAY).toInt())} назад"
        diff > DAY*360 -> "более года назад"
        diff*-1 in SECOND*75 until MINUTE*45 -> "через ${TimeUnits.MINUTE.plural((diff*-1/ MINUTE).toInt())}"
        diff*-1 in MINUTE*75 until HOUR*22 -> "через ${TimeUnits.HOUR.plural((diff*-1/ HOUR).toInt())}"
        diff*-1 in HOUR*26..DAY*360 -> "через ${TimeUnits.DAY.plural((diff*-1/ DAY).toInt())}"
        diff*-1 > DAY*360 -> "более чем через год"
        else -> ""
    }


}