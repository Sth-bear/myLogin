package com.example.myloginpage

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.app.AlertDialog
import android.content.Context


class SingInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val idEdit = findViewById<EditText>(R.id.ed_id)
        val pwEdit = findViewById<EditText>(R.id.ed_pw)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnCreate = findViewById<Button>(R.id.btn_create)

        btnLogin.isEnabled = false

        pwEdit.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val id = idEdit.text.toString()
                val pw = pwEdit.text.toString()
                btnLogin.isEnabled = id.isNotEmpty() && pw.isNotEmpty() //둘중 하나라도 없는경우 비활성화 -> toast는 회원가입에서 구현
            }
        })

        btnLogin.setOnClickListener { //홈화면으로
            val idData = idEdit.text.toString()
            val pwData = pwEdit.text.toString()

            val intent = Intent(this,HomeActivity::class.java)
            intent.putExtra("loginId",idData)
            startActivity(intent)
        }

        btnCreate.setOnClickListener {//회원가입으로
            val intent = Intent(this, SingUpActivity::class.java)
            startActivity(intent)
        }

    }
}
