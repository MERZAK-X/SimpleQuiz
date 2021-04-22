package ma.emsi.simplequiz.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.lzyzsd.circleprogress.DonutProgress
import ma.emsi.simplequiz.R

class Score : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val bReplay = findViewById<Button>(R.id.bReplay)
        val bSignOut = findViewById<Button>(R.id.bSignOut)
        val donutProgress = findViewById<DonutProgress>(R.id.donut_progress)

        donutProgress.progress = intent.getDoubleExtra("QuizScore",0.0).toInt()

        bReplay.setOnClickListener { v ->
            startActivity(Intent(v.context, QuizView::class.java))
            finish()
        }

        bSignOut.setOnClickListener { v ->
            startActivity(Intent(v.context, Login::class.java))
            finish()
        }
    }

}