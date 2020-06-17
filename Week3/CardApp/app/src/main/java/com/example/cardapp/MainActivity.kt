package com.example.cardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    
}

