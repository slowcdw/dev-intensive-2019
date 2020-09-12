package ru.skillbranch.devintensive.models
import android.util.Log
class Bender(var status:Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion() : String = when (question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String) : Pair<String, Triple<Int, Int, Int>>{
        if (question.validateAnswer(answer)?.isNotBlank()!!){
            return question.validateAnswer(answer)!!+"\n"+question.question to status.color
        }
        return if (question.answers.contains(answer.toLowerCase())){
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        }else{
            if (question == Question.IDLE)
                question.question to status.color
            else {
                status = status.nextStatus()
                if (status == Status.NORMAL) {
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                } else {
                    "Это неправильный ответ\n${question.question}" to status.color
                }
            }
        }
    }

    enum class Status(val color : Triple<Int, Int, Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

        fun nextStatus() : Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>){
        NAME("Как меня зовут?", listOf("бендер", "bender")){
            override fun nextQuestion(): Question = PROFESSION
            override fun validateAnswer(answer: String): String? {
                return if (!answer[0].isUpperCase())
                    "Имя должно начинаться с заглавной буквы"
                else ""
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")){
            override fun nextQuestion(): Question = MATERIAL
            override fun validateAnswer(answer: String): String? {
                return if (answer[0].isUpperCase())
                    "Профессия должна начинаться со строчной буквы"
                else ""
            }
        },
        MATERIAL("Из чего я сделан?", listOf("метал", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
            override fun validateAnswer(answer: String): String? {
                return if (answer.contains("\\d".toRegex()))
                    "Материал не должен содержать цифр"
                else ""
            }
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
            override fun validateAnswer(answer: String): String? {
                return if (answer.contains("[^0-9]".toRegex()))
                    "Год моего рождения должен содержать только цифры"
                else ""
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
            override fun validateAnswer(answer: String): String? {
                return if (!answer.contains("^[0-9]{7}$".toRegex()))
                    "Серийный номер содержит только цифры, и их 7"
                else ""
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
            override fun validateAnswer(answer: String): String? {
                return ""
            }
        };

        abstract fun nextQuestion() : Question
        abstract fun validateAnswer(answer: String) : String?
    }
}