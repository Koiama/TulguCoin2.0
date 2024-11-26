package com.example.a20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a20.databinding.ActivityMainBinding
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding // Объявляем binding как свойство класса
    private lateinit var navController: NavController // Объявляем NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // Устанавливаем Toolbar как ActionBar

        // Если нужно, вы можете настроить заголовок
        supportActionBar?.title = "ТулГУ coin"



        // Инициализация NavHostFragment и NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment // Убедитесь, что идентификатор совпадает
        navController = navHostFragment.navController // Получаем NavController

    }
}


