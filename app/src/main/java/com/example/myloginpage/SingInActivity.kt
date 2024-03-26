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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


class SingInActivity : AppCompatActivity() {
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val idEdit = findViewById<EditText>(R.id.ed_id)
        val pwEdit = findViewById<EditText>(R.id.ed_pw)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnCreate = findViewById<Button>(R.id.btn_create)

        btnLogin.isEnabled = false

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
                if(result.resultCode == RESULT_OK) {
                    val getId = result.data?.getStringExtra("id") ?: ""
                    val getPw = result.data?.getStringExtra("pw") ?: ""

                    idEdit.setText(getId) //.text 를 할 경우 editable 입력받아야 하기에 접근이 불가능하다. .setText로 집어넣자
                    pwEdit.setText(getPw)

            }
        }

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
            activityResultLauncher.launch(intent)
        }

    }
}
