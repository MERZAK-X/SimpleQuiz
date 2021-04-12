package ma.emsi.simplequiz

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import ma.emsi.simplequiz.entities.Answer

class Login : AppCompatActivity() {

    var mAuth = FirebaseAuth.getInstance()
    private val login get() = findViewById<EditText>(R.id.etLogin).text.toString()
    private val password get() = findViewById<EditText>(R.id.etPassword).text.toString()

    private val editTextsAreEmpty: Boolean
        get() = login.isEmpty() || password.isEmpty()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth.signOut()

        val linkToRegister = findViewById<TextView>(R.id.tvRegister)
        val bLogin = findViewById<Button>(R.id.bLogin)

        linkToRegister.movementMethod = LinkMovementMethod.getInstance()

        linkToRegister.setOnClickListener { v ->
            startActivity(Intent(v.context, Register::class.java))
            finish()
        }

        bLogin.setOnClickListener { v ->

            if (editTextsAreEmpty) Toast.makeText(
                v.context,
                "Please enter email and password !",
                Toast.LENGTH_SHORT
            ).show() else {
                connectToFireBase(v)
            }
        }
    }


    private fun connectToFireBase(v: View) {
        mAuth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener(this@Login) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    mAuth.currentUser
                    Toast.makeText(
                        v.context, "Welcome back ${mAuth.currentUser?.email}!",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(v.context, QuizView::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        v.context, "Authentication failed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}