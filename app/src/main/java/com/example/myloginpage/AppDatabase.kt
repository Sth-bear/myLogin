package com.example.myloginpage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun UserDao(): UserDao?

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