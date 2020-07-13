package com.example.mymovieinfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.properties.Delegates
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {

    private var isLoggedIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            logIn()

        }
    }

    private fun logIn() {
        var uName = username_editText.text.toString()
        var uPwd = password_editText.text.toString()

        if (uName.count() > 1 && uPwd.count() > 3) {
            isLoggedIn = true

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

            getSharedPreferences("state", Context.MODE_PRIVATE).edit()
                .putBoolean("loggedStatus", isLoggedIn).apply()

        } else println("Log-in DENIED !")

    }

    override fun onResume() {
        super.onResume()
        println("On RESUME Called $isLoggedIn")
        val sharedPreferences = getSharedPreferences("state", Context.MODE_PRIVATE) ?: return
        isLoggedIn = sharedPreferences?.getBoolean("loggedStatus", isLoggedIn)

        if (isLoggedIn) startActivity(Intent(this, MainActivity::class.java))

    }

}