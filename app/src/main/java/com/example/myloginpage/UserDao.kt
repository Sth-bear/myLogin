package com.example.myloginpage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT id FROM user")
    fun getUser(): List<String> //전체 아이디 리스트를 가져와 중복체크

    @Query("SELECT pw FROM user WHERE id = :id") //해당 id에 할당된 pw검사
    fun getPasswordById(id:String):String

    @Query("SELECT name FROM user WHERE id = :id") //회원가입된 이름도 가져와서 넘기기
    fun getNameById(id:String):String
    @Insert //유저추가
    fun insert(user: User)
}