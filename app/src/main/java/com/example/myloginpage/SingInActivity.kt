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
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


class SingInActivity : AppCompatActivity() {
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getDBInstance(this)!! //db연결

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

        pwEdit.addTextChangedListener(object :TextWatcher { //작성시 마다 상태가 변하도록 / 쓰기전, 쓰는중, 쓰고난후
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

            if(db.UserDao()?.getUser()?.contains(idData) == true) { // id 가 data에 있는지?
                if (db.UserDao()?.getPasswordById(idData) == pwData) { // id에 할당된 pw가 맞는지?
                    val userName = db.UserDao()?.getNameById(idData) // id 에 할당된 이름을 가져옴
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("loginId", idData) //id와 이름을 intent로 넘김
                    intent.putExtra("loginName", userName)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "ID 또는 PW를 확인해주세요.", Toast.LENGTH_SHORT).show()//둘중 뭐가 문제인지 모르게 같은 toast를 출력
                }
            } else {
                Toast.makeText(this, "ID 또는 PW를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        btnCreate.setOnClickListener {//회원가입으로
            val intent = Intent(this, SingUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }

    }
}
