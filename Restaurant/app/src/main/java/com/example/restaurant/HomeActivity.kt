package com.example.restaurant

import android.os.Bundle
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.restaurant.fragments.FavoritesFragment
import com.example.restaurant.fragments.HomeFragment
import com.example.restaurant.fragments.SettingsFragment
import kotlinx.android.synthetic.main.fragments.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragments)

        val firstFragment = HomeFragment()
        val secondFragment = FavoritesFragment()
        val thirdFragment = SettingsFragment()

        setCurrentFragment(firstFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.miHome -> setCurrentFragment(firstFragment)
                R.id.miFavorites -> setCurrentFragment(secondFragment)
                R.id.miSettings -> setCurrentFragment(thirdFragment)
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
       replace(R.id.flFragment,fragment)
       commit()
    }

}