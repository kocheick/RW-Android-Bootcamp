package com.example.mymovieinfo.ui.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mymovieinfo.ui.main.fragments.FavoriteMoviesFragment
import com.example.mymovieinfo.ui.main.fragments.SearchMovieFragment
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> SearchMovieFragment()
            else -> FavoriteMoviesFragment()
        }
    }

    override fun getCount(): Int {
       return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        super.getPageTitle(position)
        return when(position){
            0 -> "Movies"
            else -> "Favorites"
        }
       // return
    }
}