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
        val signupbutton: Button = findViewById(R.id.confirmButton)
        signupbutton.setOnClickListener {
            registerUser()
        }

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

         val textname:     EditText = findViewById(R.id.nameEditText)
         val textemail:    EditText = findViewById(R.id.emailEditText)
         val textpassword: EditText = findViewById(R.id.passwordEditText)
         val name: String = textname.text.toString().trim{ it <= ' '}
         val email: String = textemail.text.toString().trim{ it <= ' '}
         val password: String = textpassword.text.toString().trim{ it <= ' '}
        if (validateForm(name, email, password)) {

            showProgressDialog("Please wait")
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        val registeredEmail = firebaseUser.email!!
                        val user = User(firebaseUser.uid, name, registeredEmail)
                        FireStore().registerUser(this@SignUpActivity, user)
                        /*Toast.makeText(
                            this,
                            "$name you have successfully registered the email address $registeredEmail",
                            Toast.LENGTH_LONG
                        ).show()

                        FirebaseAuth.getInstance().signOut()
                        finish()*/
                    } else {
                        Toast.makeText(
                            this,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }

        }

    }
    fun userRegistrationSuccess() {
        Toast.makeText(
            this,
            "You have successfully registered.",
            Toast.LENGTH_SHORT
        ).show()
        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        finish()
    }


}