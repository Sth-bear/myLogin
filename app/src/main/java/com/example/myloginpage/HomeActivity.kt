package com.example.myloginpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val btnQuit = findViewById<Button>(R.id.btn_quit)
        val loginId = intent.getStringExtra("loginId")

        val userId = findViewById<TextView>(R.id.tv_id)
        userId.text = loginId

        btnQuit.setOnClickListener {
            finish()
        }
    }


}