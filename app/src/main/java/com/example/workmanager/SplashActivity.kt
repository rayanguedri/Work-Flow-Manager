package com.example.workmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {
    lateinit var haja:  ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        haja = findViewById(R.id.iv_note)



        haja.alpha = 0f
        haja.animate().setDuration(1500).alpha(1f).withEndAction{
            val currentUserID = FireStore().getCurrentUserID()
            if(currentUserID.isNotEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
            }
            else{
                startActivity(Intent(this, IntroActivity::class.java))
            }
            finish()
        }

    }


}