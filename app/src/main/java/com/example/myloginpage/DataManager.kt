package com.example.myloginpage

import android.content.Context
import android.widget.Toast


class DataManager(private val context: Context) {
    private val userList = mutableListOf<UserData>()

    fun addUser(user: UserData) {
        if(!userList.any { it.id == user.id }){
            userList.add(user)
        }
        else {
            Toast.makeText(context, "이미 사용중인 ID입니다.", Toast.LENGTH_SHORT).show()
        }
    }


}