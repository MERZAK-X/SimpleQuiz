package ma.emsi.simplequiz.activities

import androidx.appcompat.app.AppCompatActivity
import ma.emsi.simplequiz.entities.Question
import ma.emsi.simplequiz.controllers.QuizController
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import ma.emsi.simplequiz.R
import ma.emsi.simplequiz.entities.Quiz
import ma.emsi.simplequiz.factory.QuizFactory
import kotlin.coroutines.*

class QuizView : AppCompatActivity() {
    private lateinit var currentQuestion: Question
    private lateinit var quizController: QuizController
    private lateinit var imgQuestion: ImageView
    private lateinit var tvQuestion: TextView
    private lateinit var rgAnswers: RadioGroup
    private lateinit var rbAnswer1: RadioButton
    private lateinit var rbAnswer2: RadioButton
    private lateinit var btNext: Button
    private lateinit var quizName : String
    private lateinit var quiz : Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        activityViews

        quizName = intent.getStringExtra("QuizName") ?: ""

        val testFactory = QuizFactory()
        MainScope().launch {
            quiz = testFactory.getQuiz(this@QuizView.quizName)
            quizController = QuizController(quiz, this@QuizView)
            quizController.nextQuestion()
        }.start()

        btNext.setOnClickListener { v: View ->
            val checkedRadioButtonId = rgAnswers.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                val answer =
                    (findViewById<View>(checkedRadioButtonId) as RadioButton).text.toString()
                quizController.submitAnswer(currentQuestion, answer)
                if (!quizController.nextQuestion()) {
                    val intent = Intent(v.context, Score::class.java)
                    intent.putExtra("QuizScore", quizController.percentageScore())
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