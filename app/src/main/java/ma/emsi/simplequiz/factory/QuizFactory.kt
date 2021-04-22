package ma.emsi.simplequiz.factory

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ma.emsi.simplequiz.entities.Answer
import ma.emsi.simplequiz.entities.Question
import ma.emsi.simplequiz.entities.Quiz

class QuizFactory {

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun getQuiz(quizName : String) : Quiz {
        var quiz : Quiz
        val questions = mutableListOf<Question>()
        var answersMap : HashMap<String, HashMap<String,*>>
        var answersList: MutableList<Answer>
        var currentQuestion : String
        var points : Int
        var imageUrl : String


        firestore.collection("Quizzes/$quizName/Questions")
                .get()
                .addOnCompleteListener {
                    it.result?.documents?.forEach{question ->
                        answersList = mutableListOf<Answer>()
                        currentQuestion = question["value"] as String
                        imageUrl = question["image"] as String
                        points = (question["points"] as Long).toInt()
                        answersMap = question["answers"] as HashMap<String, HashMap<String,*>>
                        answersMap.forEach { answer ->
                            answersList.add(Answer(answer.value["value"] as String, answer.value["isCorrect"] as Boolean))
                        }
                        questions.add(Question(currentQuestion, points, answersList, imageUrl))
                    }

                }.await()

        quiz = Quiz(questions,"TEST")
        return quiz

    }

    suspend fun getInfo(quizName: String) : String {
        var info = ""
        firestore.document("Quizzes/$quizName")
            .get()
            .addOnSuccessListener{
                info = "About the quiz:\n${it.data?.get("info")}\n\n" +
                       "Author: ${it.data?.get("author")}"
                if (it.data?.get("info") == null)
                    info = "QUIZ NOT FOUND !"
            }.addOnFailureListener{
                info = "QUIZ NOT FOUND !"
            }.await()
        return info
    }

}