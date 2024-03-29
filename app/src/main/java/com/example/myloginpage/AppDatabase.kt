package com.example.myloginpage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao // 이전 -> UserDao() : UserDao? 변수기에 소문자로 시작, -> 선언당시 ?를 통해 널이 가능하다고 선언했기에 이후에도 .?를 사용해야함. 삭제함으로 삭제가능

    companion object {  //객체 생성에 많은 비용이 듦. 싱글톤으로 구성
        private var INSTANCE: AppDatabase? = null
        fun getDBInstance(context: Context): AppDatabase? {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,AppDatabase::class.java,"User_data"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}