package com.salihakbas.kekodprojectchallenge.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.salihakbas.kekodprojectchallenge.R
import com.salihakbas.kekodprojectchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNav, navHostFragment.navController)

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.switchFragment -> {
                    navigateToFragment(R.id.switchFragment)
                    true
                }
                R.id.happinessFragment -> {
                    navigateToFragment(R.id.happinessFragment)
                    true
                }

                R.id.optimismFragment -> {
                    navigateToFragment(R.id.optimismFragment)
                    true
                }

                R.id.kindnessFragment -> {
                    navigateToFragment(R.id.kindnessFragment)
                    true
                }

                R.id.givingFragment -> {
                    navigateToFragment(R.id.givingFragment)
                    true
                }

                else -> false
            }
        }
    }

    fun getBottomNavigationView(): BottomNavigationView {
        return binding.bottomNav
    }
    fun addMenuItem(title: String, fragmentId: Int, iconResId: Int) {
        val menu = binding.bottomNav.menu
        val itemId = fragmentId  // Menü item'ı için ID olarak fragment ID'sini kullanıyoruz
        val item = menu.findItem(itemId)

        if (item == null) {
            menu.add(0, itemId, menu.size(), title)
                .setIcon(iconResId)
                .setOnMenuItemClickListener {
                    navigateToFragment(fragmentId)
                    true
                }
        }
    }

    fun removeMenuItem(fragmentId: Int) {
        val menu = binding.bottomNav.menu
        val item = menu.findItem(fragmentId)
        item?.let {
            menu.removeItem(it.itemId)
        }
    }

    private fun navigateToFragment(fragmentId: Int) {
        navHostFragment.navController.navigate(fragmentId)
    }
}