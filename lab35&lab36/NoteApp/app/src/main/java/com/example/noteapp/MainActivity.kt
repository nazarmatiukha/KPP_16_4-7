package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.noteapp.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        val switchLocaleButton: Button = findViewById(R.id.switchLocaleButton)
        switchLocaleButton.setOnClickListener {
            switchLocale()
        }
    }
    private fun switchLocale() {
        val currentLocale = getCurrentLocale()
        val newLocale = if (currentLocale.language == "en") {
            Locale("uk")
        } else {
            Locale("en")
        }
        updateLocale(newLocale)
    }
    private fun getCurrentLocale(): Locale {
        val localeList = AppCompatDelegate.getApplicationLocales()
        return if (localeList.isEmpty) {
            Locale.getDefault()
        } else {
            localeList[0]!!
        }
    }
    private fun updateLocale(locale: Locale) {
        val localeList = LocaleListCompat.create(locale)
        AppCompatDelegate.setApplicationLocales(localeList)
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}