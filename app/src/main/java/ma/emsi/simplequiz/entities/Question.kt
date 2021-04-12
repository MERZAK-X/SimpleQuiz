package ma.emsi.simplequiz.entities

data class Question (val value : String,
                     val points : Int,
                     val answers : List<Answer>,
                     val imageUrl : String
) {
    val correctAnswer : Answer? get() = answers.findLast { a -> a.isCorrect }
}