package com.example.cardapp

import android.app.AlertDialog.*
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
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

        showDialog()

        darkModeSwitch.setOnClickListener {
            switchTheme(darkModeSwitch.isChecked)

        }

        shareButtonView.setOnClickListener {
            shareInfo()
        }

        email.setOnClickListener {
            val recipient = email.text.toString().trim()
            val subject = "I need help with.."

            sendEmail(recipient, subject)
        }


        phone.setOnClickListener {
            val number = phone.text.toString().trim()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)))
            startActivity(intent)
        }

        contact.setOnClickListener {
            val dialogTitle = contact.text.toString()
            val dialogMessage = "username: XBASS"

            Builder(this).apply {
                setTitle(dialogTitle)
                setMessage(dialogMessage)
                create()
                show()
            }
        }

        social.setOnClickListener {
            val dialogTitle = social.text.toString()
            val dialogMessage = "username: XBASS"

            Builder(this).apply {
                setTitle(dialogTitle)
                setMessage(dialogMessage)
                create()
                show()
            }
        }


        if (savedInstanceState != null) {
            val isOn = savedInstanceState.getBoolean("isDarkModeOn")
            switchTheme(isOn)


        }

    }

    private fun sendEmail(recipient: String, subject: String) {
        val intent = Intent(Intent.ACTION_SEND)

        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"

        intent.putExtra(Intent.EXTRA_EMAIL, recipient)

        intent.putExtra(Intent.EXTRA_SUBJECT, subject)

        try {
            //start email intent
            startActivity(Intent.createChooser(intent, "Choose Email Client..."))
        } catch (e: Exception) {
            //if any thing goes wrong .. show exception message
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showDialog() {
        for (view in scrollViewLinearLayout) {
            view.setOnClickListener {
                val dialogTitle = view.contentDescription.toString()
                val dialogMessage = getString(R.string.dialogMessage)

                Builder(this).apply {

                    setTitle(dialogTitle)
                    setMessage(dialogMessage)
                    create()
                    show()


                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isDarkModeOn", darkModeSwitch.isChecked)

    }

    private fun switchTheme(isSwitched: Boolean) {

        if (isSwitched) { //dark mode on

            darkModeSwitch.text = "Light"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                rootLayout.setBackgroundColor(getColor(R.color.dark_shade))
            }
            for (view in scrollViewLinearLayout) view.setBackgroundColor(getColor(R.color.dark_shade))
            name.setTextColor(Color.WHITE)
            for (view in contactGroup) if (view is TextView) {
                view.setTextColor(getColor(R.color.light_gray))
                view.setBackgroundColor(getColor(R.color.gray))
            }
            job.setTextColor(getColor(R.color.light_red))
            city.setTextColor(getColor(R.color.light_gray))
            scheduleView.setTextColor(getColor(R.color.dark_red))
            shareButtonView.setBackgroundColor(getColor(R.color.dark_shade))
            darkModeSwitch.setTextColor(Color.WHITE)

        } else { // light mode on
            darkModeSwitch.text = "Dark"

            rootLayout.setBackgroundColor(getColor(R.color.dirt_white))
            for (view in scrollViewLinearLayout) view.setBackgroundColor(getColor(R.color.dirt_white))
            name.setTextColor(getColor(R.color.gray))
            for (view in contactGroup) if (view is TextView) {
                view.setTextColor(getColor(R.color.mainColor))
                view.setBackgroundColor(getColor(R.color.ligther_gray))
            }
            job.setTextColor(getColor(R.color.dark_red))
            city.setTextColor(getColor(R.color.gray))
            scheduleView.setTextColor(getColor(R.color.light_gray))
            shareButtonView.setBackgroundColor(getColor(R.color.dirt_white))
            this.darkModeSwitch.setTextColor(getColor(R.color.mainColor))


        }

    }


    private fun loadUser(user: Person) {
        profilGroup.apply {
            name.text = getString(R.string.uName, user.fName)
            job.text = getString(R.string.userJob, user.jobTitle)
            city.text = getString(R.string.userCity, user.city)
            scheduleView.text = getString(R.string.userSchedule, user.schedule)
        }

        contactGroup.apply {

            (phone as TextView).text = getString(R.string.userPhone, user.phone)
            (email as TextView).text = getString(R.string.userEmail, user.email)
            (contact as TextView).text = getString(R.string.userSocial, user.social)
            (social as TextView).text = getString(R.string.userSocial2, user.social)
        }
    }

    private fun shareInfo() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND


            putExtra(Intent.EXTRA_TEXT, "This is my info to send.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}

