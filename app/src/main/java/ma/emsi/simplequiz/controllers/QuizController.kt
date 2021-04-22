package ma.emsi.simplequiz.controllers

import ma.emsi.simplequiz.activities.QuizView
import ma.emsi.simplequiz.entities.Question
import ma.emsi.simplequiz.entities.Quiz

class QuizController(val quiz: Quiz, val quizView: QuizView) {
    private val quizQuestionsIterator = quiz.questions.iterator()
    private var score : Double = 0.0
    private var quizTotalPoints : Double = 0.0

    fun nextQuestion() : Boolean{
        if (quizQuestionsIterator.hasNext()) {
            quizView.setQuestion(quizQuestionsIterator.next())
            return true
        }
        return false
    }

    fun submitAnswer(question : Question, answer : String) {
        val questionExistsAndAnswerIsCorrect = quiz.questions.find { q -> q == question }?.answers?.find { a -> a.value == answer && a.isCorrect }
        if (questionExistsAndAnswerIsCorrect?.isCorrect == true){
            score += question.points
        }
    }

    fun percentageScore() : Double {
        quizTotalPoints = 0.00
        quiz.questions.forEach { q -> quizTotalPoints += q.points }
        return (score * 100 / quizTotalPoints)
    }
}