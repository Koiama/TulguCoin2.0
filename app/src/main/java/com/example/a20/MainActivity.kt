package com.example.a20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a20.databinding.ActivityMainBinding
import androidx.appcompat.widget.Toolbar
import com.example.a20.bust.BustFragment


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding // Объявляем binding как свойство класса

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // Устанавливаем Toolbar как ActionBar

        // Если нужно, вы можете настроить заголовок
        supportActionBar?.title = "ТулГУ coin"

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ButtonFragment())
                .commit()
        }
    }
}


