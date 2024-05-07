package com.example.travelai.ui.activity


import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.travelai.R
import com.example.travelai.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        val menu: Menu = binding.bottomNavigation.menu
        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            val customMenuView = layoutInflater.inflate(R.layout.custom_menu,null)
            val iconImageView = customMenuView!!.findViewById<ImageView>(R.id.bottom_nav_icon)
            val textTextView = customMenuView.findViewById<TextView>(R.id.bottom_nav_text)
            iconImageView.setImageDrawable(menuItem.icon)
            textTextView.text = menuItem.title
            menuItem.actionView = customMenuView
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}