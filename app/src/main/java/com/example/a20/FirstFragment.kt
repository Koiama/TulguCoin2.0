package com.example.a20

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MessagesFragment : Fragment() {
    override fun onCreateView(
        inflater:LayoutInflater, container:ViewGroup?,
        savedInstance: Bundle?): View? {
        // Инфлейтинг(разворачивание) XML-файла разметки
        return inflater.inflate(R.layout.fragment_first, container, false)
    }
}