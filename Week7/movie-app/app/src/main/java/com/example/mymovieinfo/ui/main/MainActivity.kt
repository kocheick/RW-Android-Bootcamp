package com.example.mymovieinfo.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mymovieinfo.R
import com.example.mymovieinfo.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
       setupNavMenu()

    }

    private fun setupNavMenu() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)as NavHostFragment
        val navController = navHostFragment.findNavController()

        NavigationUI.setupWithNavController(bottomNavigationView, navController)


        //       mainViewPager.adapter = MainPagerAdapter(supportFragmentManager)
        //     mainTabLayout.setupWithViewPager(mainViewPager)

    }



//    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
//        R.id.action_logout -> {
//            logOut()
//            true
//        }
//        R.id.action_clear_list -> {
//            movieViewModel.clearMovies()
//            true
//        }
//
//        else -> super.onOptionsItemSelected(item)
//    }

    private fun logOut() {
        getSharedPreferences("state", Context.MODE_PRIVATE)
            ?.edit()
            ?.putBoolean("loggedStatus", false)
            ?.apply()

        val intent = Intent(this, LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

}