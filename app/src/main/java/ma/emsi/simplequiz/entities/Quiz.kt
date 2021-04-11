package ma.emsi.simplequiz.entities

data class Quiz (val questions : List<Question>, val author : String){
    fun quizInfo() : String {
        return "Author : $author \nNumber of questions : ${questions.size}"
    }
}