package ma.emsi.simplequiz

import androidx.appcompat.app.AppCompatActivity
import ma.emsi.simplequiz.entities.Question
import ma.emsi.simplequiz.controllers.QuizController
import android.os.Bundle
import ma.emsi.simplequiz.entities.Answer
import ma.emsi.simplequiz.entities.Quiz
import android.content.Intent
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide

class QuizView : AppCompatActivity() {
    private lateinit var currentQuestion: Question
    private lateinit var  quizController: QuizController
    private lateinit var  imgQuestion: ImageView
    private lateinit var  tvQuestion: TextView
    private lateinit var  rgAnswers: RadioGroup
    private lateinit var  rbAnswer1: RadioButton
    private lateinit var  rbAnswer2: RadioButton
    private lateinit var  btNext: Button
    /*var imgPath: String
    var question: String
    var choice1: String
    var choice2: String*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        activityViews

        ////////////////////
        val questions = mutableListOf<Question>()
        val answers = mutableListOf<Answer>()

        answers.add(Answer("TEST CORRECT", true))
        answers.add(Answer("TEST WRONG", false))

        questions.add(
            Question(
                "Test Question ???",
                4,
                answers,
                "https://firebasestorage.googleapis.com/v0/b/quizapp-e1ae2.appspot.com/o/QuizQuestionsImages%2Ftestscreen.png?alt=media&token=f1024394-de67-4907-875d-551dab2138a3"
            )
        )
        answers.clear()
        answers.add(Answer("TEST 2 CORRECT", true))
        answers.add(Answer("TEST 2 WRONG", false))
        questions.add(
            Question(
                "Test Question 2 ???",
                4,
                answers,
                "https://firebasestorage.googleapis.com/v0/b/quizapp-e1ae2.appspot.com/o/QuizQuestionsImages%2Fquestion1.jpg?alt=media&token=c8637cce-d4d6-4dd2-896c-3025ff60d5d2"
            )
        )
        val q = Quiz(questions, "MERZAK")

        ////////////////////
        quizController = QuizController(q, this)
        quizController.nextQuestion()
        btNext.setOnClickListener { v: View ->
            val checkedRadioButtonId = rgAnswers.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                val answer =
                    (findViewById<View>(checkedRadioButtonId) as RadioButton).text.toString()
                quizController!!.submitAnswer(currentQuestion!!, answer)
                if (!quizController!!.nextQuestion()) {
                    val intent = Intent(v.context, Score::class.java)
                    intent.putExtra("QuizScore", quizController!!.percentageScore())
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(v.context, "Please select an answer first !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setQuestion(quest: Question) {
        activityViews
        currentQuestion = quest
        Glide.with(this).load(currentQuestion.imageUrl).into(imgQuestion)
        tvQuestion.text = currentQuestion.value
        rgAnswers.clearCheck()
        rbAnswer1.text = currentQuestion.answers[0].value
        rbAnswer2.text = currentQuestion.answers[1].value
    }

    private val activityViews: Unit
        get() {
            imgQuestion = findViewById<View>(R.id.ivQuiz) as ImageView
            tvQuestion = findViewById<View>(R.id.tvQuestion) as TextView
            rgAnswers = findViewById<View>(R.id.rgAnswers) as RadioGroup
            rbAnswer1 = findViewById<View>(R.id.rbAnswer1) as RadioButton
            rbAnswer2 = findViewById<View>(R.id.rbAnswer2) as RadioButton
            btNext = findViewById<View>(R.id.btNext) as Button
        }

}