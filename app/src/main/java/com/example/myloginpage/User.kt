package com.example.myloginpage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) //키값을 부여할것이 필요
    val userIndex : Int = 0,
    @ColumnInfo //데이터에서 행으로 쓸꺼면 필요, pw는 필요없을지도?
    var id: String,
    @ColumnInfo
    var pw: String,
    @ColumnInfo
    var name: String
)
