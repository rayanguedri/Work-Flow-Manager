package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : BaseActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


    }


    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please input a name")
                false
            }

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


    private fun registerUser() {
        var signupbutton: Button = findViewById(R.id.confirmButton)
        var textname: EditText = findViewById(R.id.nameEditText)
        var textemail: EditText = findViewById(R.id.emailEditText)
        var textpassword: EditText = findViewById(R.id.passwordEditText)

        val name: String = textname.text.toString().trim{ it <= ' '}
        val email: String = textemail.text.toString().trim{ it <= ' '}
        val password: String = textpassword.text.toString().trim{ it <= ' '}

        if (validateForm(name, email, password)) {
            Toast.makeText(this, "Form is valid", Toast.LENGTH_SHORT).show()
        }

}}