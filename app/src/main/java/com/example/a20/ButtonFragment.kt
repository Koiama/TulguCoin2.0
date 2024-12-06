package com.example.a20

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a20.databinding.FragmentButtonBinding

class ButtonFragment : Fragment(R.layout.fragment_button) {
    private var binding: FragmentButtonBinding? = null
    private var counter: Int = 0

    private val sharedPref: SharedPreferences by lazy {
        requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private val prefEditor: SharedPreferences.Editor by lazy {
        sharedPref.edit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentButtonBinding.bind(view)

        savedInstanceState?.let{
            counter = it.getInt(KEY_COUNTER, 0)
        }

        // Загружаем значение счётчика из SharedPreferences
        counter = sharedPref.getInt(KEY_COUNTER, 0)
        // Обновляем текст счётчика
        updateCounterText()

        binding?.button?.setOnClickListener{
            counter++
            updateCounterText()
        }

        binding?.PetsButton?.setOnClickListener {
            findNavController().navigate(ButtonFragmentDirections.actionButtonToBust(counter))
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
        prefEditor.putInt(KEY_COUNTER,counter)
        prefEditor.apply()
    }


    // Обновление счетчика в TextView
    private fun updateCounterText() {
        binding?.text?.text = if (counter==0) ("Тапай!") else ("Coins: ${counter}") // Текст счётчика
    }

    // Ключи для сохранения состояния
    private companion object {
        const val KEY_COUNTER = "counter"
        const val PREFS_NAME = "user_prefs" // Имя файла SharedPreferences
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}