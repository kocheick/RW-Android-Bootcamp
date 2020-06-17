package com.example.cardapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.children
import androidx.core.view.iterator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    val user = Person(
        "SHEICK BASS",
        "SEO Expert / Digital Marketer",
        "jamaica, NY",
        "M-F: 9AM-5PM",
        "646-666-7788",
        "bass@email.me",
        "xBass"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         loadUser(user)

        darkModeSwitch.setOnClickListener {
            darkModeSwitch.text = if(darkModeSwitch.isChecked) "Light" else "Dark"

            if (darkModeSwitch.isChecked) {
                rootLayout.setBackgroundColor(getColor(R.color.dark_shade))
                for (view in scrollViewLinearLayout) view.setBackgroundColor(getColor(R.color.dark_shade))
                shareButtonView.setBackgroundColor(getColor(R.color.dark_shade))


                this.darkModeSwitch.setTextColor( Color.WHITE)

            } else {
                rootLayout.setBackgroundColor(getColor(R.color.dirt_white))
                for (view in scrollViewLinearLayout) view.setBackgroundColor(getColor(R.color.dirt_white))
                shareButtonView.setBackgroundColor(getColor(R.color.dirt_white))

                this.darkModeSwitch.setTextColor(Color.BLACK)
            }

        }

        shareButtonView.setOnClickListener {
            shareData()
        }

    }


    private fun loadUser(user:Person) {
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

