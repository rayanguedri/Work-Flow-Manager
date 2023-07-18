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

    //private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        val signinButton = findViewById<Button>(R.id.signInButton)
        //auth = FirebaseAuth.getInstance()
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
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                hideProgressDialog()
                if (task.isSuccessful) {
                    FireStore().signInUser(this@SignInActivity)
                } else {

                    Toast.makeText(
                        this@SignInActivity,
                        task.exception!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }

    fun userSignInSuccess(user: User) {
        hideProgressDialog()
       // Log.i("Sign in", "signInWithEmail:success")
        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        finish()
    }



}