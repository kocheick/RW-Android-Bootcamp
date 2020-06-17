package com.example.cardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    val user = Person(
        "Cheick Kante",
        "Digital Marketer",
        "Bronx, NY",
        "M-F: 9AM-5PM",
        "646-666-7788",
        "kocheick1@email.me",
        "kocheick"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         loadUser()

        darkModeSwitch.setOnClickListener {
            darkModeSwitch.text = if(darkModeSwitch.isChecked) "Light" else "Dark"

        }

        shareButtonView.setOnClickListener {
            shareData()
        }

    }


    private fun loadUser() {
        profilGroup.apply {
            name.text = getString(R.string.uName, user.fName)
            job.text = getString(R.string.userJob,user.jobTitle)
            city.text = getString(R.string.userCity,user.city)
            scheduleView.text = getString(R.string.userSchedule,user.schedule)
        }

        contactGroup.apply {
            phone.text = getString(R.string.userPhone,user.phone)
            email.text = getString(R.string.userEmail, user.email)
            contact.text = getString(R.string.userSocial,user.social)
            social.text = getString(R.string.userSocial2,user.social)
        }
    }

    private fun shareData() {
        val sendIntent= Intent().apply {
            action = Intent.ACTION_SEND
            title = "Share info with"

            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}

