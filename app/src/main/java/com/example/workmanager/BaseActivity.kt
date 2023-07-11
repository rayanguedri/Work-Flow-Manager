package com.example.workmanager

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

open class BaseActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    private lateinit var mProgressDialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }


fun showProgressDialog(text: String) {
    mProgressDialog = Dialog(this)

    mProgressDialog.setContentView(R.layout.dialog_progress)


    val dialogText = mProgressDialog.findViewById<TextView>(R.id.tv_progress_message)

    dialogText.text = text

    mProgressDialog.setCancelable(false)
    mProgressDialog.setCanceledOnTouchOutside(false)

    mProgressDialog.show()
    }

    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }

    fun getCurrentUserID(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

    fun doubleBackToExit() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true

        val dialogText2 = mProgressDialog.findViewById<TextView>(R.id.please_click_back_again_to_exit)

        Toast.makeText(this, resources.getString(R.string.click_again_to_exit), Toast.LENGTH_SHORT).show()



    }

    fun showErrorSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackbar.view
        snackBarView.setBackgroundColor(
            ContextCompat.getColor(this, R.color.snackbar_error_color
            )
        )
        snackbar.show()

    }

}