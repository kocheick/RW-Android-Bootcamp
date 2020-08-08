package com.example.mymovieinfo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mymovieinfo.R
import com.example.mymovieinfo.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class LoginActivity : AppCompatActivity() {

    private var isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            logIn()

        }
    }

    private fun logIn() {
        val uName = username_editText.text.toString()
        val uPwd = password_editText.text.toString()

        if (uName.count() > 1 && uPwd.count() > 3) {
            isLoggedIn = true

            getSharedPreferences("state", Context.MODE_PRIVATE).edit()
                .putBoolean("loggedStatus", isLoggedIn).apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()


        } else Snackbar.make(login_container,
            R.string.login_denied, Snackbar.LENGTH_SHORT).show()

    }


    override fun onStart() {
        super.onStart()
        val sharedPreferences = getSharedPreferences("state", Context.MODE_PRIVATE) ?: return
        isLoggedIn = sharedPreferences.getBoolean("loggedStatus", isLoggedIn)
        if (isLoggedIn) startActivity(Intent(this, MainActivity::class.java))
    }

}