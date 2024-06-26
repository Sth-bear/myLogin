package com.example.myloginpage

import android.content.Intent
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
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        val editName = findViewById<EditText>(R.id.edit_name)
        val editId = findViewById<EditText>(R.id.edit_id)
        val editPw = findViewById<EditText>(R.id.edit_pw)
        val btnCreate = findViewById<Button>(R.id.btn_create)
        var count: Int = 0

        db = AppDatabase.getDBInstance(this.applicationContext)!!

        btnCreate.setOnClickListener {
            val userName = editName.text.toString()
            val userId = editId.text.toString()
            val userPw = editPw.text.toString()
            if (userName.isNotBlank() && userId.isNotBlank() && userPw.isNotBlank()) { // isNotEmpty() -> isNotBlank 비어있는 값이 필요한 경우도 있음 -> 입력값없음과 null은 다른것.
                if(db.userDao().getUser().contains(userId)) { //id가 이미 있다면? 가입되었다고 출력
                    Toast.makeText(this, "이미 가입된 계정입니다.", Toast.LENGTH_SHORT).show()
                } else {
                    db.userDao().insert(User(id = userId, pw = userPw, name = userName)) //신규 사용자 등록
                    val intent = Intent(this, SingInActivity::class.java)
                    intent.putExtra("id", userId)
                    intent.putExtra("pw", userPw)
                    setResult(RESULT_OK, intent)//결과값 콜백
                    finish()
                }
            } else { //그만!
                when (count) {
                    in 3..4 -> {
                        val test =
                            layoutInflater.inflate(R.layout.test_toast, findViewById(R.id.test1))
                        val testToast = Toast(applicationContext) //toast에 임의로으로 그림을 할당. text는 반드시 필요함. + toast의 아이콘은 manifest의 라운드아이콘을 따라감. 앱아아콘과 동일시됨.
                        testToast.view = test                       //즉, 아이콘만 변경하고 싶다면 여기서 매니패스트를 건드는 방식을 하면 가능해보임
                        testToast.show()
                        count++
                    }
                    5 -> {
                        val test =
                            layoutInflater.inflate(R.layout.test_toast2, findViewById(R.id.test2))
                        val testToast = Toast(applicationContext)
                        testToast.view = test
                        testToast.show()
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
