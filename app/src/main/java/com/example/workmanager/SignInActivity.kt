package com.example.workmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class SignInActivity : BaseActivity() {

     lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        val signinButton = findViewById<Button>(R.id.signInButton)
        auth = FirebaseAuth.getInstance()
        signinButton.setOnClickListener {
            signInRegisteredUser()
        }
    }



    private fun validateForm(email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please input an email")
                false
            }

            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please input a password")
                false
            }
            else -> {
                true
            }
        }
    }

    private fun signInRegisteredUser() {

        val textemailsignin:    EditText = findViewById(R.id.emailsignin)
        val textpasswordsignin: EditText = findViewById(R.id.passwordsignin)
        val email: String = textemailsignin.text.toString().trim{ it <= ' '}
        val password: String = textpasswordsignin.text.toString().trim{ it <= ' '}

    if (validateForm(email, password)) {
        showProgressDialog(resources.getString(R.string.please_wait))
    }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                hideProgressDialog()
                if (task.isSuccessful) {
                    Log.d("Sign in", "signInWithEmail:success")
                    val user = auth.currentUser
                    val intent2 = Intent(this, MainActivity::class.java)
                    startActivity(intent2)

                    //updateUI(user)
                } else {
                    Log.w("Sign in", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }

    }



}