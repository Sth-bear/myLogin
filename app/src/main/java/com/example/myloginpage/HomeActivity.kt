package com.example.myloginpage


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        db = AppDatabase.getDBInstance(this)!!


        val btnQuit = findViewById<Button>(R.id.btn_quit)
        val loginId = intent.getStringExtra("loginId")
        val loginName = intent.getStringExtra("loginName")
        val loginIndex = db.userDao().getIndexById(loginId.toString())
        Log.d("test1", "onCreate: ${loginIndex}")

        val userImg = findViewById<ImageView>(R.id.iv_user)
        //랜덤 대신 회원가입시 지정된 키값에 대응하게끔. <- != null문이 필요했으나, dao가 nullable상태가 아니기에 해당 구문도 필요없어짐.
        when (loginIndex.toInt()%5) {
            1 -> {
                userImg.setImageResource(R.drawable.user_img1)
            }
            2 -> {
                userImg.setImageResource(R.drawable.user_img2)
            }
            3 -> {
                userImg.setImageResource(R.drawable.user_img3)
            }
            4 -> {
                userImg.setImageResource(R.drawable.user_img4)
            }
            0 -> {
                userImg.setImageResource(R.drawable.user_img5)
            }
        }

        val userId = findViewById<TextView>(R.id.tv_id)
        userId.text = loginId

        val userName = findViewById<TextView>(R.id.tv_name)
        userName.text = loginName

        btnQuit.setOnClickListener {
            finish()
        }
    }

}

