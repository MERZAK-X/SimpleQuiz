package ma.emsi.simplequiz.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ma.emsi.simplequiz.R

class Register : AppCompatActivity() {

    var mAuth = FirebaseAuth.getInstance()
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var passwordConfirm: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tvLinkToLogin = findViewById<TextView>(R.id.tvToLogin)
        val bRegister = findViewById<Button>(R.id.bRegister)
        val etEmail = findViewById<EditText>(R.id.etRegEmail)
        val etPassword = findViewById<EditText>(R.id.etRegPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etRegConfirmPassword)

        tvLinkToLogin.setOnClickListener { v ->
            startActivity(Intent(v.context, Login::class.java))
            finish()
        }

        bRegister.setOnClickListener { v ->

            getTextValues(etEmail, etPassword, etConfirmPassword)

            if (emptyFields(email, password))
                Toast.makeText( v.context,"Enter you information first!", Toast.LENGTH_SHORT).show()
            else {
                if (password == passwordConfirm && !emptyFields( email, password))
                    register(v)
                else Toast.makeText(v.context,"Passwords do not match. Try Again!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun register(v: View) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this@Register) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    Toast.makeText(
                        v.context, "Sign-up Successful !",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(v.context, Login::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        v.context, "Sign-up failed: " + task.exception?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun getTextValues(etEmail: EditText, etPassword: EditText, etConfirmPassword: EditText) {
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        passwordConfirm = etConfirmPassword.text.toString()
    }

    private fun emptyFields(email: String?, password: String?): Boolean {
        return email == "" || password == ""
    }
}