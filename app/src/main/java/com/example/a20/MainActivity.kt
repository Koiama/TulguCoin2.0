package com.example.a20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import com.example.a20.databinding.ActivityMainBinding
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {
    private var counter: Int = 0
    private lateinit var binding: ActivityMainBinding // Объявляем binding как свойство класса

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Инициализируем binding
        setContentView(binding.root)

        // Инициализация Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // Устанавливаем Toolbar как ActionBar

        // Если нужно, вы можете настроить заголовок
        supportActionBar?.title = "ТулГУ coin"

        savedInstanceState?.let {
            counter = it.getInt(KEY_COUNTER, 0)
        }

        // Загружаем значение счётчика из SharedPreferences
        loadCounter()

        updateCounterText()

        binding.button.setOnClickListener {
            counter++
            updateCounterText()
        }
    }

    // Сохранение состояния
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, counter)
    }

    //Сохранение счётчика в SharedPreferences
    override fun onPause() {
        super.onPause()
        saveCounter()
    }

    //Загружаем значение счётчика из SharedPreferences
    private fun loadCounter(){
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        counter = sharedPreferences.getInt(KEY_COUNTER,0)
    }

    //Сохраняем значение счётчика в SharedPreferences
    private fun saveCounter() {
        val sharedPreferences = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
        with(sharedPreferences.edit()){
            putInt(KEY_COUNTER,counter)
            apply()
        }
    }

    // Обновление счетчика в TextView
    private fun updateCounterText() {
        binding.text.text = if (counter==0) ("Тапай!") else ("Coins: ${counter}") // Текст счётчика
    }

    // Ключи для сохранения состояния
    private companion object {
        const val KEY_COUNTER = "counter"
        const val PREFS_NAME = "user_prefs" // Имя файла SharedPreferences
    }
}
