package ma.emsi.simplequiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.lzyzsd.circleprogress.DonutProgress

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
            //QuizView.point = 0
            //QuizView.counter = 1
            finish()
        }

        bSignOut.setOnClickListener { v ->
            startActivity(Intent(v.context, Login::class.java))
            finish()
        }
    }

    /*companion object {
        @JvmField
        var result = (QuizView.point * 100 / 5).toFloat()
    }*/
}