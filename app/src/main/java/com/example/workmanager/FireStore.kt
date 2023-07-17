package com.example.workmanager

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class FireStore  {
    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo:User) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user.",
                    e
                )
            }
    }

    fun signInUser(activity: Activity ) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener {document ->
                Log.e(activity.javaClass.simpleName, document.toString())
                val loggedInUser = document.toObject(User::class.java)!!

                when(activity) {
                    is SignInActivity -> {
                        activity.userSignInSuccess(loggedInUser)
                    }
                    is MainActivity -> {
                        activity.updateNavigationUserDetails(loggedInUser)
                    }
                }
            }
            .addOnFailureListener { e ->
                   when(activity) {
                        is SignInActivity -> {
                            Toast.makeText(
                                activity,
                                "Error while registering the user.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is MainActivity -> {
                            Toast.makeText(
                                activity,
                                "Error while registering the user.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


               }
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user.",
                    e
                )
            }
    }

    fun getCurrentUserID(): String {
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

}