package ma.emsi.simplequiz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.text.set
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ma.emsi.simplequiz.R
import ma.emsi.simplequiz.factory.QuizFactory

class QuizChooser : AppCompatActivity() {

    private lateinit var etQuizID : EditText
    private lateinit var etQuizInfo : TextView
    private lateinit var btSearchQuiz : Button
    private lateinit var btStartQuiz : Button
    private var quizName = ""
    private var quizFactory = QuizFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_chooser)

        activityViews

        btSearchQuiz.setOnClickListener { v ->
            quizName = etQuizID.text.toString()
            if(quizName.isNotEmpty()){
                MainScope().launch {
                    val info = quizFactory.getInfo(quizName)
                    etQuizInfo.text = info
                    btStartQuiz.isEnabled = (info != "QUIZ NOT FOUND !") // if the quiz was found enable the start button, otherwise, disable it
                }
            }else
                Toast.makeText(v.context, "Please input a Quiz ID first !", Toast.LENGTH_SHORT).show()
        }

        btStartQuiz.setOnClickListener { v ->
            if(quizName.isNotEmpty() && etQuizInfo.text.toString() != "QUIZ NOT FOUND !"){
                val intent = Intent(v.context, QuizView::class.java)
                intent.putExtra("QuizName", quizName)
                startActivity(intent)
                finish()
            }else
                Toast.makeText(v.context, "Quiz not found! Please input a valid Quiz ID and try again !", Toast.LENGTH_SHORT).show()
        }

    }

    private val activityViews: Unit
        get() {
            etQuizID = findViewById<View>(R.id.etQuizID) as EditText
            etQuizInfo = findViewById<View>(R.id.tvQuizInfo) as TextView
            btSearchQuiz = findViewById<View>(R.id.btSearchQuiz) as Button
            btStartQuiz = findViewById<View>(R.id.btStartQuiz) as Button
        }

}