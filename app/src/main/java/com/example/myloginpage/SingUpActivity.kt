package com.example.myloginpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class SingUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        val dataManager = DataManager(this)
        val editName = findViewById<EditText>(R.id.edit_name)
        val editId = findViewById<EditText>(R.id.edit_id)
        val editPw = findViewById<EditText>(R.id.edit_pw)
        val btnCreate = findViewById<Button>(R.id.btn_create)
        var count: Int = 0

        btnCreate.setOnClickListener {
            val userName = editName.text.toString()
            val userId = editId.text.toString()
            val userPw = editPw.text.toString()
            if (userName.isNotEmpty() && userId.isNotEmpty() && userPw.isNotEmpty()) {
//                val user = UserData(name = userName, id = userId, pw = userPw) -> 이렇게한다면, 해당 화면이 종료되고 다시 불러올때 초기화되기에 날라감. 외부에 저장하는 방법으로 바꿀것.
//                dataManager.addUser(user)
                count = 0
                finish()
            } else { //그만!
                when (count) {
                    in 3..4 -> {
                        val test =
                            layoutInflater.inflate(R.layout.test_toast, findViewById(R.id.test1))
                        val testToast = Toast(applicationContext) //toast에 임의적으로 그림을 할당. text는 비어있더라도 반드시 있어야했음.
                        testToast.view = test
                        testToast.show()
                        count++
                    }
                    5 -> {
                        val test =
                            layoutInflater.inflate(R.layout.test_toast2, findViewById(R.id.test2))
                        val testToast = Toast(applicationContext)
                        testToast.view = test
                        testToast.show()
                        count = 0
                        Handler(Looper.getMainLooper()).postDelayed({
                            moveTaskToBack(true)
                            finishAndRemoveTask()
                            android.os.Process.killProcess(android.os.Process.myPid())//딜레이를 주지 않을경우 toast가 나오기전에 종료됌.
                        },1000)
                    }
                    else -> {
                        Toast.makeText(this, R.string.error_create, Toast.LENGTH_SHORT).show() // 다른곳에서 불러올경우 R.타입을 사용
                        count ++
                    }
                }
            }
        }

    }
}