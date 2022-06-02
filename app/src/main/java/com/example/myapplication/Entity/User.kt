package com.example.myapplication.Entity

class User : java.io.Serializable {
    lateinit var mail: String
    lateinit var pwd: String
    var isAuth: Boolean = false
}